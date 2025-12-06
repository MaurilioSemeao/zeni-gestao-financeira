/* eslint-disable @next/next/no-img-element */
'use client';
import { Button } from 'primereact/button';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';
import { Dialog } from 'primereact/dialog';
import { InputNumber } from 'primereact/inputnumber';
import { InputText } from 'primereact/inputtext';
import { Toast } from 'primereact/toast';
import { Toolbar } from 'primereact/toolbar';
import { classNames } from 'primereact/utils';
import React, {useEffect, useMemo, useState } from 'react';
import { useCrud } from '@/hook/useEntityCrud';
import { createFormHandlers } from '@/utils/formHandlers';
import { LeftToolbarTemplate } from '@/app/(main)/components/Templates/LeftToolbarTemplate';
import { RightToolbarTemplate } from '@/app/(main)/components/Templates/RightToolbarTemplate';
import { GenericBodyTemplate } from '@/app/(main)/components/Templates/GenericBodyTemplate';

import { cartaoService } from '@/service/CartaoService';
import { transactionService } from '@/service/TransactionService';
import { Zeni } from '@/types/zeni';




const TansactionPage = () => {
    const emptyCard : Zeni.Card ={
        id: 0,
        apelido: '',
        ultimosDigitos: '',
        gastos: 0,
        quantidadeCompras: 0,
        limitValue: 0
    }


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
        hideDeleteDialog,
        hideDeleteEntitiesDialog,
        saveEntity,
        editEntity,
        confirmDeleteEntity,
        deleteEntityById,
        confirmDeleteSelected,
        deleteSelectedEntities,
        exportCSV,
        formatCurrency,

    } = useCrud<Zeni.Card>(emptyCard)

    const {onInputChange,onInputNumberChange} = createFormHandlers<Zeni.Card>(setEntity);

    const [refresh, setRefresh] = useState(false);

    useEffect(() => {
        (async function loadData(){
            const [cartaoData] = await Promise.all([ cartaoService.getAll()])
            setEntities(cartaoData)
            console.log(cartaoData)
        }())
        if(entities.length === 0){
            setRefresh(true)
        }

    }, [entities.length, setEntities, setRefresh, refresh]);


    const leftToolbarTemplate = () => {
        return (
            <LeftToolbarTemplate<Zeni.Card>
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

    const codeBodyTemplate = (rowData: Zeni.Card) => {
        return (<GenericBodyTemplate title={"Code"} value={rowData.id} />);
    };

    const surnameBodyTemplate = (rowData: Zeni.Card) => {
        return (<GenericBodyTemplate title={"apelido"} value={rowData.apelido} />);
    };

    const quantityBodyTemplate = (rowData: Zeni.Card) => {
        return (<GenericBodyTemplate title={"quantidadeCompras"} value={rowData.quantidadeCompras} />);
    };

    const expenseBodyTemplate = (rowData: Zeni.Card) => {
        return (<GenericBodyTemplate title={"gastos"} value={formatCurrency(rowData.gastos)} />);
    };

    const finalNumberBodyTemplate = (rowData: Zeni.Card) => {
        return (<GenericBodyTemplate title={"ultimosDigitos"} value={rowData.ultimosDigitos} />);
    };



    const actionBodyTemplate = (rowData: Zeni.Card) => {
        return (
            <>
                <Button icon="pi pi-pencil" rounded severity="success" className="mr-2" onClick={() => editEntity(rowData)} />
                <Button icon="pi pi-trash" rounded severity="warning" onClick={() => confirmDeleteEntity(rowData)} />
            </>
        );
    };

    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">Gerenciar Cartoes</h5>
            <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search" />
                <InputText type="search" onInput={(e) => setGlobalFilter(e.currentTarget.value)} placeholder="Search..." />
            </span>
        </div>
    );



    const saving=() =>{
        saveEntity(cartaoService).then(r => {} )
    }

    const deleteById = () =>{
        deleteEntityById(transactionService).then(r => {})
    }

    const userDialogFooter = (
        <>
            <Button label="Cancelar" icon="pi pi-times" text onClick={hideDialog} />
            <Button label="Salvar" icon="pi pi-check" text onClick={saving} />
        </>
    );
    const deleteEntityDialogFooter = (
        <>
            <Button label="Não" icon="pi pi-times" text onClick={hideDeleteEntitiesDialog} />
            <Button label="Sim" icon="pi pi-check" text onClick={deleteById} />
        </>
    );
    const deleteUsersDialogFooter = (
        <>
            <Button label="Não" icon="pi pi-times" text onClick={hideDeleteEntitiesDialog} />
            <Button label="Yes" icon="pi pi-check" text onClick={deleteSelectedEntities} />
        </>
    );

    return (
        <div className="grid crud-demo">
            <div className="col-12">
                <div className="card">
                    <Toast ref={toast} />
                    <Toolbar className="mb-4" left={leftToolbarTemplate} right={rightToolbarTemplate}></Toolbar>

                    <DataTable
                        ref={dt}
                        value={entities}
                        selection={selectedEntities}
                        onSelectionChange={(e) => setSelectedEntities(e.value as any)}
                        dataKey="id"
                        paginator
                        rows={10}
                        rowsPerPageOptions={[5, 10, 25]}
                        className="datatable-responsive"
                        paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                        currentPageReportTemplate="Showing {first} to {last} of {totalRecords} products"
                        globalFilter={globalFilter}
                        emptyMessage="No products found."
                        header={header}

                    >
                        <Column selectionMode="multiple" headerStyle={{width: '4rem' }}></Column>
                        <Column field="surname" header="Apelido" sortable body={surnameBodyTemplate} headerStyle={{ minWidth: '12rem' }}></Column>
                        <Column field="expesne" header="Gastos" body={expenseBodyTemplate} sortable headerStyle={{ minWidth: '10rem' }}></Column>
                        <Column field="quantity" header="Compras Realizadas" sortable body={quantityBodyTemplate} headerStyle={{ minWidth: '10rem' }}></Column>

                        <Column field="finalNumber" header="Digitos" sortable body={finalNumberBodyTemplate} headerStyle={{ minWidth: '4rem' }}></Column>
                        <Column body={actionBodyTemplate} headerStyle={{ minWidth: '10rem' }}></Column>
                    </DataTable>

                    <Dialog visible={entityDialog} style={{ width: '400px' }} header="Detalhes Usuario" modal className="p-fluid" footer={userDialogFooter} onHide={hideDialog}>

                        <div className="field">
                            {submitted && !entity.id && <small className="p-invalid">Name is required.</small>}
                        </div>

                        <div className="field">
                            <label htmlFor="apelido">Apelido</label>
                            <InputText
                                id="apelido"
                                value={entity.apelido}
                                onChange={(e) => onInputChange(e, 'apelido')}
                                required
                                autoFocus
                                className={classNames({ 'p-invalid':submitted && !entity.apelido, })}
                            />
                        </div>


                        <div className="field col">
                            <label htmlFor="ultimosDigitos">Ultimos 4 Digitos</label>
                            <InputText
                                id="ultimosDigitos"
                                value={entity.ultimosDigitos}
                                onChange={(e) => onInputChange(e, 'ultimosDigitos')}
                                required
                                className={classNames({ 'p-invalid': submitted && !entity.ultimosDigitos })}
                            />
                        </div>


                    </Dialog>

                    <Dialog visible={deleteEntityDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteEntityDialogFooter} onHide={hideDeleteDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {entity && (
                                <span>
                                    Voce Realmente Deseja excluir o cartão <b>{entity.apelido}</b>?
                                </span>
                            )}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteEntitiesDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteUsersDialogFooter} onHide={hideDeleteEntitiesDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {entity && <span>Voce Realmente Deseja excluir os cartões ?</span>}
                        </div>
                    </Dialog>
                </div>
            </div>
        </div>
    );
};


export default TansactionPage;
