/* eslint-disable @next/next/no-img-element */
'use client';
import { Button } from 'primereact/button';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';
import React, { useContext, useEffect, useMemo, useRef, useState } from 'react';
import { ChartData, ChartOptions } from 'chart.js';
import { CardAccount } from '@/app/(main)/components/Cards/CardAccount';
import { CardTipoDeGasto } from '@/app/(main)/components/Cards/CardTiposDeGasto';
import { CardGrafic } from '@/app/(main)/components/Cards/CardGrafic';
import { useCrud } from '@/hook/useEntityCrud';
import { cartaoService } from '@/service/CartaoService';

import {useDashboard} from '@/context/DashBoardContext';
import { Zeni } from '@/types/zeni';
import { GenericBodyTemplate } from '@/app/(main)/components/Templates/GenericBodyTemplate';
import Transaction = Zeni.Transaction;


const Dashboard = () => {

    const {user, cards, invoices, transactions, resumoCategoria, loading, fetchData} = useDashboard();


    useEffect(()=>{
        fetchData().then(r => {});
    },[fetchData])

    if(loading){
        return <div>Carregando seu dashbiard...</div>
    }

    const carOrder = [...cards].sort((a, b) => b.gastos - a.gastos)


    const formatCurrency = (value: number) => {
        return value?.toLocaleString('en-US', {
            style: 'currency',
            currency: 'USD'
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
            <div className={`${cards.length <=0 ? "pt-0": "" } col-12 xl:col-11`}>

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

                <div className="col-12 xl:col-12">
                    <div className="grid">
                        <div className="col-12 xl:col-6">
                            <div className="card h-full">
                                <h5>Ultimas compras</h5>
                                <DataTable value={transactions} rows={4} paginator responsiveLayout="scroll" sortField="id" sortOrder={-1}>
                                    <Column field="description" header="Description" sortable body={descriptionBodyTemplate} headerStyle={{ minWidth: '10em' }}></Column>
                                    <Column field="price" header="Price" body={priceBodyTemplate} sortable headerStyle={{ minWidth: '7em' }}></Column>
                                    <Column field="date" header="Date" sortable body={dateBodyTemplate} headerStyle={{ minWidth: '7.7rem' }}></Column>

                                </DataTable>
                            </div>
                        </div>
                        <div className="col-12 xl:col-6">
                            <CardTipoDeGasto dados={resumoCategoria}/>
                        </div>
                    </div>
                </div>
            </div>

            <div className="col-12 xl:col-6">
                <CardGrafic />
            </div>
        </div>
    );
};

export default Dashboard;
