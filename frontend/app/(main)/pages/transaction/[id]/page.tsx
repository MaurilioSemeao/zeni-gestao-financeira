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
import React, {useEffect,useState } from 'react';
import { useCrud } from '@/hook/useEntityCrud';
import { createFormHandlers } from '@/utils/formHandlers';
import { LeftToolbarTemplate } from '@/app/(main)/components/Templates/LeftToolbarTemplate';
import { RightToolbarTemplate } from '@/app/(main)/components/Templates/RightToolbarTemplate';
import { GenericBodyTemplate } from '@/app/(main)/components/Templates/GenericBodyTemplate';
import { transactionService } from '@/service/TransactionService';
import { useParams, useSearchParams } from 'next/navigation';





const Crud = () => {

    const emptyCategory : Zeni.Category ={
        id: 0,
        nome: ''
    }

    let emptyTransaction: Zeni.Transaction = {
        id: 0,
        descricao: '',
        valor: 0,
        tipo: 'DESPESA',
        meioPagamento: '',
        dataTransacao: '',
        categoriaId: 0,
        cartaoId: null,
        contaId: null,
        categoria: emptyCategory,
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

    } = useCrud<Zeni.Transaction>(emptyTransaction)

    console.log(entities)

    const params = useParams();
    const id= params.id;

    const searchParams = useSearchParams();

    const  status  = searchParams.get('status')?.trim();

    const data = searchParams.get('data');


    const {onInputChange,onInputNumberChange} = createFormHandlers<Zeni.Transaction>(setEntity);
    const [refresh, setRefresh] = useState(false);

    useEffect(() => {
        (async function loadData(){
            const [transactionData] = await Promise.all([ transactionService.getAllById(`extratomensal/${id}`)])
            setEntities(transactionData)
        }())
        if(entities.length === 0){
            setRefresh(true)
        }

    }, [entities.length, id, setEntities, setRefresh]);

    const leftToolbarTemplate = () => {
        return (
            <LeftToolbarTemplate<Zeni.Transaction>
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


    const descriptionBodyTemplate = (rowData: Zeni.Transaction) => {
        return (
            <GenericBodyTemplate
                title={"Code"}
                value={rowData.descricao}
            />
        );
    };

    const priceBodyTemplate = (rowData: Zeni.Transaction) => {
        return (
            <GenericBodyTemplate
                title={"Code"}
                value={formatCurrency(rowData.valor)}
            />
        );
    };


    const dateBodyTemplate = (rowData: Zeni.Transaction) => {

        return (
            <GenericBodyTemplate
                title={"Code"}
                value={rowData.dataTransacao}
            />
        );
    };

    const meioDePagamentoBodyTemplate = (rowData: Zeni.Transaction) => {
        return (<GenericBodyTemplate title={"MeioDePagamento"} value={rowData.meioPagamento} />);
    };

    const categoriaBodyTemplate = (rowData: Zeni.Transaction) => {
        // @ts-ignore
        return (<GenericBodyTemplate title={"Categoria"} value={rowData.categoria.nome} />);
    };




    const actionBodyTemplate = (rowData: Zeni.Transaction) => {
        return (
            <>
                <Button icon="pi pi-pencil" rounded severity="success" className="mr-2" onClick={() => editEntity(rowData)} />
                <Button icon="pi pi-trash" rounded severity="warning" onClick={() => confirmDeleteEntity(rowData)} />
            </>
        );
    };

    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">Transação Mês: {data} </h5>
            <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search" />
                <InputText type="search" onInput={(e) => setGlobalFilter(e.currentTarget.value)} placeholder="Search..." />
            </span>
        </div>
    );



    const saving=() =>{
        saveEntity(transactionService).then(r => {} )
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
    const deleteEntitiesDialogFooter = (
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
                    <Toolbar className="mb-4" left={status === 'ABERTA' ? leftToolbarTemplate : null} right={rightToolbarTemplate}></Toolbar>

                    <DataTable
                        ref={dt}
                        value={entities}
                        selection={selectedEntities}
                        onSelectionChange={(e) => setSelectedEntities(e.value as any)}
                        dataKey="id"
                        sortField="id"
                        sortOrder={-1}
                        paginator
                        rows={10}
                        rowsPerPageOptions={[5, 10, 25]}
                        className="datatable-responsive"
                        paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                        currentPageReportTemplate="Showing {first} to {last} of {totalRecords} products"
                        globalFilter={globalFilter}
                        emptyMessage="No products found."
                        header={header}
                        responsiveLayout="scroll"
                    >
                        <Column selectionMode="multiple" headerStyle={{ width: '4rem' }}></Column>
                        <Column field="descricao" header="Descrição" sortable body={descriptionBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column field="valor" header="Valor" body={priceBodyTemplate} sortable></Column>
                        <Column field="data" header="Data" sortable body={dateBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        <Column field="meioDePagamento" header="Meio de Pagamento" sortable body={meioDePagamentoBodyTemplate} headerStyle={{ minWidth: '2rem' }}></Column>
                        <Column field="categoria" header="Categoria" sortable body={categoriaBodyTemplate} headerStyle={{ minWidth: '1rem' }}></Column>
                        {status == 'ABERTA'
                            ? <Column body={actionBodyTemplate} headerStyle={{ minWidth: '10rem' }}></Column>
                            : <Column field="fechada" header="Fechada" sortable body={"fechada"} headerStyle={{ minWidth: '15rem' }}></Column>}
                    </DataTable>

                    <Dialog visible={entityDialog} style={{ width: '450px' }} header="Detalhes Usuario" modal className="p-fluid" footer={userDialogFooter} onHide={hideDialog}>
                        <div className="field">{submitted && !entity.id && <small className="p-invalid">Name is required.</small>}</div>
                        <div className="field">
                            <label htmlFor="description">Descrição</label>
                            <InputText id="description" value={entity.descricao} onChange={(e) => onInputChange(e, 'descricao')} required autoFocus className={classNames({ 'p-invalid': submitted && !entity.descricao })} />
                        </div>

                        <div className="field col">
                            <label htmlFor="price">Price</label>
                            <InputNumber
                                id="price"
                                value={entity.valor}
                                onValueChange={(e) => onInputNumberChange(e, 'valor')}
                                mode="currency"
                                currency="BRL"
                                locale="pt-BR"
                                required
                                autoFocus
                                className={classNames({ 'p-invalid': submitted && !entity.valor })}
                            />
                        </div>
                    </Dialog>

                    <Dialog visible={deleteEntityDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteEntityDialogFooter} onHide={hideDeleteDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {entity && (
                                <span>
                                   Voce Realmente deseja excluir a transação <b>{entity.descricao}</b>?
                                </span>
                            )}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteEntitiesDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteEntitiesDialogFooter} onHide={hideDeleteEntitiesDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {entity && <span>Você realmente deseja excluir as transações ?</span>}
                        </div>
                    </Dialog>
                </div>
            </div>
        </div>
    );
};


export default Crud;
