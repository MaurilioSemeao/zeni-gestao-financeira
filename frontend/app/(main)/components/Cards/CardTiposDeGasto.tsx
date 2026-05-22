import React from 'react';

interface CardTipoDeGastoProps {
    dados: Zeni.ResumoCategoria[];
}

export const CardTipoDeGasto = ({dados}:CardTipoDeGastoProps) =>{

    const PALETA_CORES = [
        { bg: 'bg-blue-500', text: 'text-blue-500' },
        { bg: 'bg-green-500', text: 'text-green-500' },
        { bg: 'bg-orange-500', text: 'text-orange-500' },
        { bg: 'bg-purple-500', text: 'text-purple-500' },
        { bg: 'bg-cyan-500', text: 'text-cyan-500' },
        { bg: 'bg-pink-500', text: 'text-pink-500' },
        { bg: 'bg-yellow-500', text: 'text-yellow-500' },
        { bg: 'bg-teal-500', text: 'text-teal-500' },
        { bg: 'bg-indigo-500', text: 'text-indigo-500' },
        { bg: 'bg-red-500', text: 'text-red-500' },
    ];

    const getColorForCategory = (nomeCategoria: string) => {
        let hash = 0;
        for (let i = 0; i < nomeCategoria.length; i++) {
            hash = nomeCategoria.charCodeAt(i) + ((hash << 5) - hash);
        }
        const index = Math.abs(hash % PALETA_CORES.length);
        return PALETA_CORES[index];
    };

    return (

        <div className="card h-full">
            <div className="flex justify-content-between align-items-center mb-5">
                <h5>Gastos Por Categoria</h5>
                <div>

                </div>
            </div>


            <ul className="list-none p-0 m-0">
                {dados.map((item, index) => {
                    const corTema = getColorForCategory(item.nomeCategoria);
                    return (
                        <li key={index} className="flex flex-column md:flex-row md:align-items-center md:justify-content-between mb-4">
                            <div>
                                <span className="text-900 font-medium mr-2 mb-1 md:mb-0">{item.nomeCategoria}</span>
                                <div className="mt-1 text-600">---------</div>
                            </div>
                            <div className="mt-2 md:mt-0 flex align-items-center">
                                <div className="surface-300 border-round overflow-hidden w-10rem lg:w-6rem" style={{ height: '8px' }}>
                                    <div className={`${corTema.bg} h-full`} style={{ width: item.porcentagem }} />
                                </div>
                                <span className={`${corTema.text} h-full ml-3 font-medium `} >%{item.porcentagem}</span>
                            </div>
                        </li>
                    )
                })}

            </ul>
        </div>

    )
}

