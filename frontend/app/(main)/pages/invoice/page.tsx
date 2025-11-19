/* eslint-disable @next/next/no-img-element */
'use client';
import { Button } from 'primereact/button';
import { InputText } from 'primereact/inputtext';
import { Toast } from 'primereact/toast';
import { Toolbar } from 'primereact/toolbar';
import React, {useEffect, useMemo, useState } from 'react';
import { useCrud } from '@/hook/useEntityCrud';
import { createFormHandlers } from '@/utils/formHandlers';
import { LeftToolbarTemplate } from '@/app/(main)/components/Templates/LeftToolbarTemplate';
import { RightToolbarTemplate } from '@/app/(main)/components/Templates/RightToolbarTemplate';
import { GenericBodyTemplate } from '@/app/(main)/components/Templates/GenericBodyTemplate';
import { invoiceService } from '@/service/InvoiceService';
import { DataView } from 'primereact/dataview';
import { Tag } from 'primereact/tag';
import { Rating } from 'primereact/rating';
import Link from 'next/link';
import {Zeni} from '@/types/zeni';


const Crud = () => {
    let emptyInvoice: Zeni.Invoice = {
        id: 0,
        valorTotal: 0,
        status: '',
        mesReferencia: '',
    };


    const {
        entities,
        setEntities,
        entity,
        setEntity,
        entityDialog,
        deleteEntityDialog,
        deleteEntitiesDialog,
        selectedEntities,
        setSelectedEntities,
        submitted,
        globalFilter,
        setGlobalFilter,
        toast,
        dt,

        openNew,
        hideDialog,
        hideDeleteEntitiesDialog,
        saveEntity,
        editEntity,
        confirmDeleteEntity,
        deleteEntityById,
        confirmDeleteSelected,
        deleteSelectedEntities,
        exportCSV,
        formatCurrency,

    } = useCrud<Zeni.Invoice>(emptyInvoice)

    const {onInputChange,onInputNumberChange} = createFormHandlers<Zeni.Invoice>(setEntity);

    const [refresh, setRefresh] = useState(false);

    useEffect(() => {
        //ProductService.getProducts().then((data) => setProducts(data as any));
        (async function loadData(){
            const [invoiceData] = await Promise.all([ invoiceService.getAll()])

            setEntities(invoiceData);
        }())
        if(entities.length === 0){
            setRefresh(true)
        }

    }, [entities.length, refresh, setEntities, invoiceService]);


    const leftToolbarTemplate = () => {
        return (
            <LeftToolbarTemplate<Zeni.Invoice>
                openNew={openNew}
                confirmDeleteSelected={confirmDeleteSelected}
                selectedGeneric={selectedEntities}
            />
        );
    };

    const rightToolbarTemplate = () => {
        return (
            <RightToolbarTemplate
                exportCSV={exportCSV}
            />
        );
    };

    const codeBodyTemplate = (rowData: Zeni.Invoice) => {
        return (
            <GenericBodyTemplate
                title={"Code"}
                value={rowData.id}
            />
        );
    };

    const statusBodyTemplate = (rowData: Zeni.Invoice) => {
        return (
            <GenericBodyTemplate
                title={"Status"}
                value={rowData.status}
            />
        );
    };

    const valorTotalBodyTemplate = (rowData: Zeni.Invoice) => {
        return (
            <GenericBodyTemplate
                title={"Valor Total"}
                value={formatCurrency(rowData.valorTotal)}
            />
        );
    };


      const mesRefBodyTemplate = (rowData: Zeni.Invoice) => {
          console.log(rowData);
        return (
            <GenericBodyTemplate
                title={"Mes Referencia"}
                value={rowData.mesReferencia}
            />
        );
    };



    const actionBodyTemplate = (rowData: Zeni.Invoice) => {
        return (
            <>
                <Button icon="pi pi-pencil" rounded severity="success" className="mr-2" onClick={() => editEntity(rowData)} />
                <Button icon="pi pi-trash" rounded severity="warning" onClick={() => confirmDeleteEntity(rowData)} />
            </>
        );
    };

    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">Gerenciar Fatura</h5>
            <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search" />
                <InputText type="search" onInput={(e) => setGlobalFilter(e.currentTarget.value)} placeholder="Search..." />
            </span>
        </div>
    );



    const saving=() =>{
        saveEntity(invoiceService).then(r => {} )
    }

    const userDialogFooter = (
        <>
            <Button label="Cancelar" icon="pi pi-times" text onClick={hideDialog} />
            <Button label="Salvar" icon="pi pi-check" text onClick={saving} />
        </>
    );
    const deleteUserDialogFooter = (
        <>
            <Button label="Não" icon="pi pi-times" text onClick={hideDeleteEntitiesDialog} />
            <Button label="Sim" icon="pi pi-check" text onClick={deleteEntityById} />
        </>
    );
    const deleteUsersDialogFooter = (
        <>
            <Button label="Não" icon="pi pi-times" text onClick={hideDeleteEntitiesDialog} />
            <Button label="Yes" icon="pi pi-check" text onClick={deleteSelectedEntities} />
        </>
    );


    const listItem = (invoice: Zeni.Invoice) => {


        const transactionUrl = `
        /pages/transaction/${invoice.id}
        ?status=${invoice.status}
        &value=${invoice.valorTotal}
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
                            <Rating value={invoice.valorTotal} readOnly cancel={false}></Rating>
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
                            <span className="text-2xl font-semibold">${formatCurrency(invoice.valorTotal)}</span>
                        </div>

                    </div>
                </div>

            </Link>
        );
    };

        const getSeverity = (status: string) => {
            switch (status) {
                case 'ABERTA':
                    return 'success';   // verde
                case 'LOWSTOCK':
                    return 'warning';   // amarelo
                case 'OUTOFSTOCK':
                    return 'danger';    // vermelho
                default:
                    return null;
            }
        };

        const gridItem = (invoice: Zeni.Invoice) => {
            return (
                <div className="col-12 sm:col-6 lg:col-4 xl:col-3 p-2">
                    <div className="p-4 border-1 surface-border surface-card border-round flex flex-column align-items-center">
                        <img
                            src="/card1.png"
                            alt={invoice.status}
                            className="w-9 shadow-2 border-round mb-3"
                        />
                        <div className="text-lg font-bold mb-1">{invoice.status}</div>
                        <div className="mb-2">{invoice.mesReferencia}</div>
                        <Tag value={invoice.valorTotal} severity={getSeverity(invoice.status)} className="mb-2"></Tag>
                        <span className="text-2xl font-semibold mb-3">${entity.valorTotal}</span>
                        <Button icon="pi pi-shopping-cart" rounded></Button>
                    </div>
                </div>
            );
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
                    <Toolbar className="mb-4" left={leftToolbarTemplate} right={rightToolbarTemplate}></Toolbar>

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
                        {/*<Column selectionMode="multiple" headerStyle={{ width: '4rem' }}></Column>*/}
                        {/*<Column field="code" header="Code" sortable body={codeBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>*/}
                        {/*<Column field="description" header="Description" sortable body={statusBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>*/}
                        {/*<Column field="price" header="Price" body={valorTotalBodyTemplate} sortable></Column>*/}
                        {/*<Column field="date" header="Date" sortable body={mesRefBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>*/}
                        {/*<Column body={actionBodyTemplate} headerStyle={{ minWidth: '10rem' }}></Column>*/}
                    </DataView>



                </div>
            </div>
        </div>
    );
};


export default Crud;
