import React from 'react';
import { useDashboard } from '@/context/DashBoardContext';

interface CardTiposDeGastoCartaoProps {
    dados: Zeni.ResumoCartao[];
}

export const CardTiposDeGastoCartao = ({ dados }: CardTiposDeGastoCartaoProps) => {
    const PALETA_CORES = [
        { bg: 'bg-blue-500', text: 'text-blue-500' },
        { bg: 'bg-purple-500', text: 'text-purple-500' },
        { bg: 'bg-orange-500', text: 'text-orange-500' },
        { bg: 'bg-green-500', text: 'text-green-500' },
        { bg: 'bg-cyan-500', text: 'text-cyan-500' },
        { bg: 'bg-pink-500', text: 'text-pink-500' },
        { bg: 'bg-yellow-500', text: 'text-yellow-500' },
        { bg: 'bg-teal-500', text: 'text-teal-500' },
        { bg: 'bg-indigo-500', text: 'text-indigo-500' },
        { bg: 'bg-red-500', text: 'text-red-500' },
    ];

    const getColorForCard = (apelidoCartao: string) => {
        let hash = 0;
        for (let i = 0; i < apelidoCartao.length; i++) {
            hash = apelidoCartao.charCodeAt(i) + ((hash << 5) - hash);
        }
        const index = Math.abs(hash % PALETA_CORES.length);
        return PALETA_CORES[index];
    };

    return (
        <div className="card h-full">
            <div className="flex flex-column mb-5">
                <h5>Gastos Por Cartão</h5>
                <span className="text-500 text-sm">Resumo de gastos consolidados no período selecionado</span>
            </div>

            {dados.length === 0 ? (
                <div className="flex justify-content-center align-items-center py-5 text-500">
                    Nenhum gasto registrado em cartões de crédito neste período.
                </div>
            ) : (
                <ul className="list-none p-0 m-0">
                    {dados.map((item, index) => {
                        const corTema = getColorForCard(item.apelidoCartao);
                        const formatadoValor = new Intl.NumberFormat('pt-BR', {
                            style: 'currency',
                            currency: 'BRL'
                        }).format(item.valorTotal);

                        return (
                            <li key={index} className="flex flex-column md:flex-row md:align-items-center md:justify-content-between mb-4">
                                <div className="flex-1">
                                    <span className="text-900 font-semibold mr-2">
                                        {item.apelidoCartao}
                                    </span>
                                    <span className="text-600 text-sm">
                                        (**** {item.ultimosDigitos})
                                    </span>
                                    <div className="mt-1 text-500 text-xs">
                                        {item.quantidadeCompras} {item.quantidadeCompras === 1 ? 'compra' : 'compras'} • {formatadoValor}
                                    </div>
                                </div>
                                <div className="mt-2 md:mt-0 flex align-items-center">
                                    <div className="surface-300 border-round overflow-hidden w-10rem lg:w-6rem" style={{ height: '8px' }}>
                                        <div className={`${corTema.bg} h-full`} style={{ width: `${item.porcentagem}%` }} />
                                    </div>
                                    <span className={`${corTema.text} h-full ml-3 font-semibold`} style={{ minWidth: '45px', textAlign: 'right' }}>
                                        %{item.porcentagem.toFixed(0)}
                                    </span>
                                </div>
                            </li>
                        );
                    })}
                </ul>
            )}
        </div>
    );
};
