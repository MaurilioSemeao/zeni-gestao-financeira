/* eslint-disable @next/next/no-img-element */
'use client';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';
import React, { useEffect } from 'react';
import { CardAccount } from '@/app/(main)/components/Cards/CardAccount';
import { CardTipoDeGasto } from '@/app/(main)/components/Cards/CardTiposDeGasto';
import { CardTiposDeGastoCartao } from '@/app/(main)/components/Cards/CardTiposDeGastoCartao';
import { CardGrafic } from '@/app/(main)/components/Cards/CardGrafic';
import {useDashboard} from '@/context/DashBoardContext';
import { GenericBodyTemplate } from '@/app/(main)/components/Templates/GenericBodyTemplate';


const Dashboard = () => {

    const {cards, transactions, resumoCategoria, resumoCartao, loading, fetchData} = useDashboard();


    useEffect(()=>{
        fetchData().then(r => {});
    },[fetchData])

    if(loading){
        return <div>Carregando seu dashboard...</div>
    }

    const carOrder = [...cards].sort((a, b) => b.gastos - a.gastos)


    const formatCurrency = (value: number) => {
        return value?.toLocaleString('pt-BR', {
            style: 'currency',
            currency: 'BRL'
        });
    };

    const descriptionBodyTemplate = (rowData: Zeni.Transaction) => {
        return (<GenericBodyTemplate title={"descricao"} value={rowData.descricao} />);
    };

    const priceBodyTemplate = (rowData: Zeni.Transaction) => {
        return (<GenericBodyTemplate title={"price"} value={formatCurrency(rowData.valor)} />);
    };

    const dateBodyTemplate = (rowData: Zeni.Transaction) => {
        return (<GenericBodyTemplate title={"data"} value={rowData.dataTransacao} />);
    };

    return (
        <div className="grid">
            <div className={`${cards.length <=0 ? "pt-0": "" } col-12`}>

                {cards.length > 0 ?
                    <div className="flex flex-nowrap overflow-x-auto gap-3 ml-2 pb-2">

                        {carOrder
                            .map((cad: Zeni.Card) => {
                            return <CardAccount key={cad.id} surname={cad.apelido} finalNumber={cad.ultimosDigitos} expenses={cad.gastos} quantityOfPurchases={cad.quantidadeCompras} />;
                        })}
                    </div>
                :
                    null
                }

                <div className="col-12 mt-3">
                    <div className="grid">
                        <div className="col-12 xl:col-6">
                            <div className="card h-full">
                                <h5>Últimas compras</h5>
                                <DataTable value={transactions} rows={4} paginator sortField="id" sortOrder={-1}>
                                    <Column field="Descricao" header="Descrição" sortable body={descriptionBodyTemplate} headerStyle={{ minWidth: '10em' }}></Column>
                                    <Column field="valor" header="Valor" body={priceBodyTemplate} sortable headerStyle={{ minWidth: '7em' }}></Column>
                                    <Column field="data" header="Data Hora" sortable body={dateBodyTemplate} headerStyle={{ minWidth: '7.7rem' }}></Column>

                                </DataTable>
                            </div>
                        </div>
                        <div className="col-12 xl:col-6">
                            <CardTipoDeGasto dados={resumoCategoria}/>
                        </div>
                        <div className="col-12 xl:col-6">
                            <CardTiposDeGastoCartao dados={resumoCartao}/>
                        </div>
                    </div>
                </div>
            </div>

            {/* <div className="col-12 xl:col-6">
                <CardGrafic />
            </div> */}
        </div>
    );
};

export default Dashboard;

