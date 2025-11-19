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
import { transactionService } from '@/service/TransactionService';
import {cartaoService} from '@/service/CartaoService';
import { Dropdown } from 'primereact/dropdown';
import { Zeni } from '@/types/zeni';




const TansactionPage = () => {
    let emptyTransaction: Zeni.Transaction = {
        id: 0,
        description: '',
        price: 0,
        data: '',
        cardId: 0,
    };

    const emptyCard : Zeni.Card ={
        id: 0,
        surname: '',
        finalNumber: '',
        expenses: 0,
        quantityOfPurchases: 0,
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

    const {onInputChange,onInputNumberChange} = createFormHandlers<Zeni.Transaction>(setEntity);


    const [refresh, setRefresh] = useState(false);
    const cards= useCrud<Zeni.Card>(emptyCard);



    useEffect(() => {
        //ProductService.getProducts().then((data) => setProducts(data as any));
        (async function loadData(){
            const [transactionData] = await Promise.all([ transactionService.getAll()])
            const [carData] = await Promise.all([ cartaoService.getAll()])
            setEntities(transactionData);
            cards.setEntities(carData);
        }())
        if(entities.length === 0){
            setRefresh(true)
        }

    }, [cards.setEntities, entities.length, setEntities, setRefresh, ]);


    const onSelectCardChange = (card: Zeni.Card) => {
        let _entity = {...entity}
        _entity.cardId = card.id
        setEntity(_entity)
    }

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



    const codeBodyTemplate = (rowData: Zeni.Transaction) => {
        return (<GenericBodyTemplate title={"Code"} value={rowData.id} />);
    };

    const descriptionBodyTemplate = (rowData: Zeni.Transaction) => {
        return (<GenericBodyTemplate title={"Code"} value={rowData.description} />);
    };

    const priceBodyTemplate = (rowData: Zeni.Transaction) => {
        return (<GenericBodyTemplate title={"Code"} value={formatCurrency(rowData.price)} />);
    };

    const dateBodyTemplate = (rowData: Zeni.Transaction) => {
        return (<GenericBodyTemplate title={"Code"} value={rowData.data} />);
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
            <h5 className="m-0">Gerenciar Transações</h5>
            <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search" />
                <InputText type="search" onInput={(e) => setGlobalFilter(e.currentTarget.value)} placeholder="Search..." />
            </span>
        </div>
    );



    const saving=() =>{
        saveEntity(transactionService).then(r => {} )
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
                        sortField="id"
                        sortOrder={-1}
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
                        <Column field="description" header="Description" sortable body={descriptionBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
                        <Column field="price" header="Price" body={priceBodyTemplate} sortable></Column>
                        <Column field="date" header="Date" sortable body={dateBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
                        <Column body={actionBodyTemplate} headerStyle={{ minWidth: '10rem' }}></Column>
                    </DataTable>

                    <Dialog visible={entityDialog} style={{ width: '450px' }} header="Detalhes Usuario" modal className="p-fluid" footer={userDialogFooter} onHide={hideDialog}>

                        <div className="field">
                            {submitted && !entity.id && <small className="p-invalid">Name is required.</small>}
                        </div>
                        <div className="field">
                            <label htmlFor="description">Descrição</label>
                            <InputText
                                id="description"
                                value={entity.description}
                                onChange={(e) => onInputChange(e, 'description')}
                                required
                                autoFocus
                                className={classNames({ 'p-invalid':submitted && !entity.description, })}
                            />
                        </div>

                        <div className="field col">
                            <label htmlFor="price">Price</label>
                            <InputNumber
                                id="price"
                                onValueChange={(e) => onInputNumberChange(e, 'price')}
                                mode="currency"
                                currency="BRL"
                                locale="pt-BR"
                                required
                                autoFocus
                                className={classNames({ 'p-invalid': submitted && !entity.price })}
                            />
                        </div>

                        <div className="field">
                            <label htmlFor="description">Cartao</label>
                            <Dropdown
                                id="card"
                                value={cards.entities.find(c => c.id === entity.cardId)}
                                options={cards.entities}
                                onChange={(e) => onSelectCardChange(e.value)}
                                optionLabel="surname"
                                placeholder="Selecion um Cartao"
                                className={classNames({ 'p-invalid': submitted && !entity.cardId })}
                            />
                            {submitted && !entity.cardId && <small className="p-invalid">Name is required.</small>}
                        </div>

                    </Dialog>

                    <Dialog visible={deleteEntityDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteUserDialogFooter} onHide={hideDeleteEntitiesDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {entity && (
                                <span>
                                    Voce Realmente Deseja excluir o usuario <b>{entity.description}</b>?
                                </span>
                            )}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteEntitiesDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteUsersDialogFooter} onHide={hideDeleteEntitiesDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {entity && <span>Voce Realmente Deseja excluir o usuario ?</span>}
                        </div>
                    </Dialog>
                </div>
            </div>
        </div>
    );
};


export default TansactionPage;
