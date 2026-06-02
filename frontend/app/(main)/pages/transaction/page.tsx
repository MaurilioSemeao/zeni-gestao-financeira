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
import React, { useEffect, useMemo, useState } from 'react';
import { useCrud } from '@/hook/useEntityCrud';
import { createFormHandlers } from '@/utils/formHandlers';
import { LeftToolbarTemplate } from '@/app/(main)/components/Templates/LeftToolbarTemplate';
import { RightToolbarTemplate } from '@/app/(main)/components/Templates/RightToolbarTemplate';
import { GenericBodyTemplate } from '@/app/(main)/components/Templates/GenericBodyTemplate';
import { transactionService } from '@/service/TransactionService';
import { cartaoService } from '@/service/CartaoService';
import { Dropdown } from 'primereact/dropdown';
import { RadioButton } from 'primereact/radiobutton';
import { meiosPagamento } from '@/types/constats.';
import { categoriaService } from '@/service/CategoriaService';

const TransactionPage = () => {
    const emptyCategory: Zeni.Category = {
        id: 0,
        nome: ''
    };

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
        categoria: emptyCategory
    };

    const emptyCard: Zeni.Card = {
        id: 0,
        apelido: '',
        ultimosDigitos: '',
        gastos: 0,
        quantidadeCompras: 0,
        limitValue: 0
    };

    const emptyResumoCategoria: Zeni.ResumoCategoria = {
        id: 0,
        nomeCategoria: '',
        valorTotal: 0,
        porcentagem: 0
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
    } = useCrud<Zeni.Transaction>(emptyTransaction);

    const { onInputChange, onInputNumberChange } = createFormHandlers<Zeni.Transaction>(setEntity);

    const [refresh, setRefresh] = useState(false);
    const cards = useCrud<Zeni.Card>(emptyCard);
    const category = useCrud<Zeni.Category>(emptyCategory);

    useEffect(() => {
        (async function loadData() {
            const [transactionData] = await Promise.all([transactionService.getAll()]);
            const [categoriaData] = await Promise.all([categoriaService.getAll()]);
            const [carData] = await Promise.all([cartaoService.getAll()]);

            setEntities(transactionData);
            cards.setEntities(carData);
            category.setEntities(categoriaData);
        })();
        if (entities.length === 0) {
            setRefresh(true);
        }
    }, [cards, cards.setEntities, category, entities.length, setEntities, setRefresh]);

    const onSelectCardChange = (card: Zeni.Card) => {
        let _entity = { ...entity };
        _entity.cartaoId = card.id;
        setEntity(_entity);
    };

    const onSelectCategoryChange = (category: Zeni.Category) => {
        let _entity = { ...entity };
        _entity.categoriaId = category.id;
        setEntity(_entity);
    };

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
        return <RightToolbarTemplate exportCSV={exportCSV} />;
    };

    const descriptionBodyTemplate = (rowData: Zeni.Transaction) => {
        return <GenericBodyTemplate title={"Descricao"} value={rowData.descricao} />;
    };

    const priceBodyTemplate = (rowData: Zeni.Transaction) => {
        return <GenericBodyTemplate title={"Valor"} value={formatCurrency(rowData.valor)} />;
    };

    const dateBodyTemplate = (rowData: Zeni.Transaction) => {
        return <GenericBodyTemplate title={"Data"} value={rowData.dataTransacao} />;
    };

    const meioDePagamentoBodyTemplate = (rowData: Zeni.Transaction) => {
        return <GenericBodyTemplate title={"MeioDePagamento"} value={rowData.meioPagamento} />;
    };

    const categoriaBodyTemplate = (rowData: Zeni.Transaction) => {
        // @ts-ignore
        return <GenericBodyTemplate title={"Categoria"} value={rowData.categoria?.nome || 'Sem Categoria'} />;
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
                <InputText type="search" onInput={(e) => setGlobalFilter(e.currentTarget.value)} placeholder="Pesquisar..." />
            </span>
        </div>
    );

    const saving = () => {
        saveEntity(transactionService).then(() => {
            setRefresh(prev => !prev);
        });
    };

    const deleteById = () => {
        deleteEntityById(transactionService).then(() => {
            setRefresh(prev => !prev);
        });
    };

    const userDialogFooter = (
        <>
            <Button label="Cancelar" icon="pi pi-times" text onClick={hideDialog} />
            <Button label="Salvar" icon="pi pi-check" text onClick={saving} />
        </>
    );

    const deleteEntityDialogFooter = (
        <>
            <Button label="Não" icon="pi pi-times" text onClick={hideDeleteDialog} />
            <Button label="Sim" icon="pi pi-check" text onClick={deleteById} />
        </>
    );

    const deleteEntitiesDialogFooter = (
        <>
            <Button label="Não" icon="pi pi-times" text onClick={hideDeleteEntitiesDialog} />
            <Button label="Sim" icon="pi pi-check" text onClick={deleteSelectedEntities} />
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
                        currentPageReportTemplate="Exibindo {first} a {last} de {totalRecords} registros"
                        globalFilter={globalFilter}
                        emptyMessage="Nenhuma transação encontrada."
                        header={header}
                        responsiveLayout="scroll"
                    >
                        <Column selectionMode="multiple" headerStyle={{ width: '4rem' }}></Column>
                        <Column field="descricao" header="Descrição" sortable body={descriptionBodyTemplate} headerStyle={{ minWidth: '10rem' }}></Column>
                        <Column field="valor" header="Valor" body={priceBodyTemplate} sortable></Column>
                        <Column field="data" header="Data" sortable body={dateBodyTemplate} headerStyle={{ minWidth: '8rem' }}></Column>
                        <Column field="meioDePagamento" header="Meio de Pagamento" sortable body={meioDePagamentoBodyTemplate} headerStyle={{ minWidth: '10rem' }}></Column>
                        <Column field="categoria" header="Categoria" sortable body={categoriaBodyTemplate} headerStyle={{ minWidth: '10rem' }}></Column>
                        <Column body={actionBodyTemplate} headerStyle={{ minWidth: '8rem' }}></Column>
                    </DataTable>

                    <Dialog visible={entityDialog} style={{ width: '450px' }} header="Detalhes da Transação" modal className="p-fluid" footer={userDialogFooter} onHide={hideDialog}>
                        <div className="field">
                            {submitted && !entity.descricao && <small className="p-error block">Campo obrigatório.</small>}
                        </div>
                        <div className="field">
                            <label htmlFor="description">Descrição</label>
                            <InputText
                                id="description"
                                value={entity.descricao}
                                onChange={(e) => onInputChange(e, 'descricao')}
                                required
                                autoFocus
                                className={classNames({ 'p-invalid': submitted && !entity.descricao })}
                            />
                        </div>

                        <div className="field">
                            <label htmlFor="valor">Valor</label>
                            <InputNumber
                                id="valor"
                                value={entity.valor}
                                onValueChange={(e) => onInputNumberChange(e, 'valor')}
                                mode="currency"
                                currency="BRL"
                                locale="pt-BR"
                                required
                                className={classNames({ 'p-invalid': submitted && !entity.valor })}
                            />
                        </div>
                        
                        <div className="field">
                            <label className="mb-3 font-semibold">Tipo de Transação</label>
                            <div className="formgrid grid">
                                <div className="field-radiobutton col-6">
                                    <RadioButton inputId="tipoDespesa" name="tipo" value="DESPESA" onChange={(e) => setEntity({ ...entity, tipo: e.value })} checked={entity.tipo === 'DESPESA'} />
                                    <label htmlFor="tipoDespesa">Despesa</label>
                                </div>
                                <div className="field-radiobutton col-6">
                                    <RadioButton inputId="tipoReceita" name="tipo" value="RECEITA" onChange={(e) => setEntity({ ...entity, tipo: e.value })} checked={entity.tipo === 'RECEITA'} />
                                    <label htmlFor="tipoReceita">Receita</label>
                                </div>
                            </div>
                            {submitted && !entity.tipo && <small className="p-error block">Selecione o tipo.</small>}
                        </div>

                        <div className="formgrid grid">
                            <div className="field col-12">
                                <label htmlFor="categoriaDeGastos">Categoria de Gastos</label>
                                <Dropdown
                                    id="categoriaDeGastos"
                                    value={category.entities.find((c) => c.id === entity.categoriaId)}
                                    options={category.entities}
                                    onChange={(e) => onSelectCategoryChange(e.value)}
                                    optionLabel="nome"
                                    placeholder="Selecione uma Categoria"
                                    className={classNames({ 'p-invalid': submitted && !entity.categoriaId })}
                                />
                                {submitted && !entity.categoriaId && <small className="p-error block">Campo obrigatório.</small>}
                            </div>
                        </div>

                        <div className="formgrid grid">
                            <div className="field col-12 md:col-6">
                                <label htmlFor="meioDePagamento">Método de Pagamento</label>
                                <Dropdown
                                    id="meioDePagamento"
                                    value={entity.meioPagamento}
                                    options={meiosPagamento}
                                    onChange={(e) => setEntity({ ...entity, meioPagamento: e.value, cartaoId: null, contaId: null })}
                                    optionLabel="label"
                                    placeholder="Selecione um método de Pagamento"
                                    className={classNames({ 'p-invalid': submitted && !entity.meioPagamento })}
                                />
                                {submitted && !entity.meioPagamento && <small className="p-error block">Campo obrigatório.</small>}
                            </div>

                            {entity.meioPagamento === 'CREDITO' && (
                                <div className="field col-12 md:col-6">
                                    <label htmlFor="card">Cartão</label>
                                    <Dropdown
                                        id="card"
                                        value={cards.entities.find((c) => c.id === entity.cartaoId)}
                                        options={cards.entities}
                                        onChange={(e) => onSelectCardChange(e.value)}
                                        optionLabel="apelido"
                                        placeholder="Selecione um Cartão"
                                        className={classNames({ 'p-invalid': submitted && !entity.cartaoId })}
                                    />
                                    {submitted && !entity.cartaoId && <small className="p-error block">Campo obrigatório.</small>}
                                </div>
                            )}

                            {(entity.meioPagamento === 'DEBITO' || entity.meioPagamento === 'PIX') && (
                                <div className="field col-12 md:col-6">
                                    <label htmlFor="conta">Conta</label>
                                    <Dropdown
                                        id="conta"
                                        value={cards.entities.find((c) => c.id === entity.contaId)}
                                        options={cards.entities}
                                        onChange={(e) => onSelectCardChange(e.value)}
                                        optionLabel="apelido"
                                        placeholder="Selecione uma Conta"
                                        className={classNames({ 'p-invalid': submitted && !entity.contaId })}
                                    />
                                    {submitted && !entity.contaId && <small className="p-error block">Campo obrigatório.</small>}
                                </div>
                            )}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteEntityDialog} style={{ width: '450px' }} header="Confirmação" modal footer={deleteEntityDialogFooter} onHide={hideDeleteDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {entity && (
                                <span>
                                    Você realmente deseja excluir a transação <b>{entity.descricao}</b>?
                                </span>
                            )}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteEntitiesDialog} style={{ width: '450px' }} header="Confirmação" modal footer={deleteEntitiesDialogFooter} onHide={hideDeleteEntitiesDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {entity && <span>Você realmente deseja excluir as transações selecionadas?</span>}
                        </div>
                    </Dialog>
                </div>
            </div>
        </div>
    );
};

export default TransactionPage;
