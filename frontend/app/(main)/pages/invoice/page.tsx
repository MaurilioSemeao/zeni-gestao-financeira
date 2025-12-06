/* eslint-disable @next/next/no-img-element */
'use client';
import { InputText } from 'primereact/inputtext';
import { Toast } from 'primereact/toast';
import React, {useEffect,  useState } from 'react';
import { useCrud } from '@/hook/useEntityCrud';
import { invoiceService } from '@/service/InvoiceService';
import { DataView } from 'primereact/dataview';
import { Tag } from 'primereact/tag';
import { Rating } from 'primereact/rating';
import Link from 'next/link';
import {Zeni} from '@/types/zeni';



const Crud = () => {
    let emptyInvoice: Zeni.Invoice = {
        id: 0,
        saldoExtrato: 0,
        status: '',
        mesReferencia: '',
    };


    const {
        entities,
        setEntities,
        setGlobalFilter,
        toast,
        formatCurrency,

    } = useCrud<Zeni.Invoice>(emptyInvoice)



    const [refresh, setRefresh] = useState(false);

    useEffect(() => {
        //ProductService.getProducts().then((data) => setProducts(data as any));
        (async function loadData(){
            const [invoiceData] = await Promise.all([ invoiceService.getAll()])
            console.log(invoiceData);
            setEntities(invoiceData);
        }())
        if(entities.length === 0){
            setRefresh(true)
        }

    }, [entities.length, refresh, setEntities]);


    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">Gerenciar Fatura</h5>
            <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search" />
                <InputText type="search" onInput={(e) => setGlobalFilter(e.currentTarget.value)} placeholder="Search..." />
            </span>
        </div>
    );



    const listItem = (invoice: Zeni.Invoice) => {


        const transactionUrl = `
        /pages/transaction/${invoice.id}
        ?status=${invoice.status}
        &value=${invoice.saldoExtrato}
        &data=${invoice.mesReferencia}
        `;


        return (

            <Link
                href={transactionUrl}
                key={invoice.id}
                className="col-12"
                style={{ textDecoration: 'none', color: 'inherit' }}
            >

                <div className="flex flex-column xl:flex-row xl:align-items-start p-4 gap-4 border-1 surface-border border-round surface-card">
                    <div className="flex flex-column sm:flex-row justify-content-between align-items-center flex-1 gap-4">


                        <div className="flex flex-column align-items-center sm:align-items-start gap-3">
                            <Rating value={invoice.saldoExtrato} readOnly cancel={false}></Rating>
                            <div className="flex align-items-center gap-3">
                            <span className="flex align-items-center gap-2">
                               Mes  <i className="pi pi-caret-right"></i>
                                <span className="font-semibold">{invoice.mesReferencia}</span>
                            </span>
                            </div>
                            <div className="flex flex-column sm:flex-row justify-content-between align-items-center flex-1 gap-4">
                                <Tag value={invoice.status} severity={getSeverity(invoice.status)}></Tag>
                            </div>
                        </div>
                        <div className="flex sm:flex-column align-items-center sm:align-items-end gap-3 sm:gap-2">
                            <span className="text-2xl font-semibold">${formatCurrency(invoice.saldoExtrato)}</span>
                        </div>

                    </div>
                </div>

            </Link>
        );
    };

        const getSeverity = (status: string) => {
            switch (status) {
                case 'ABERTA':
                    return 'success';
                case 'FECHADA':
                    return 'danger';
                default:
                    return null;
            }
        };



        const itemTemplate = (invoice: any, layout: 'list') => {
            if (!invoice) return null;
                return listItem(invoice);
        };

    return (
        <div className="grid crud-demo">
            <div className="col-12">
                <div className="card">
                    <Toast ref={toast} />
                    <DataView
                        value={entities}
                        dataKey="id"
                        paginator
                        rows={10}
                        rowsPerPageOptions={[5, 10, 25]}
                        itemTemplate={itemTemplate}
                        className="datatable-responsive"
                        paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                        currentPageReportTemplate="Showing {first} to {last} of {totalRecords} products"
                        emptyMessage="No products found."
                        header={header}
                    >

                    </DataView>



                </div>
            </div>
        </div>
    );
};


export default Crud;
