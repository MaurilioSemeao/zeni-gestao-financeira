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

import { cartaoService } from '@/service/CartaoService';
import { dashBoradService } from '@/service/DashBoardService';

const CardsPage = () => {
    const emptyCard: Zeni.Card = {
        id: 0,
        apelido: '',
        ultimosDigitos: '',
        gastos: 0,
        quantidadeCompras: 0,
        limitValue: 0
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
    } = useCrud<Zeni.Card>(emptyCard);

    const { onInputChange } = createFormHandlers<Zeni.Card>(setEntity);

    const [refresh, setRefresh] = useState(false);
    const [periodo, setPeriodo] = useState<string>('MENSAL');
    const [resumoCartao, setResumoCartao] = useState<Zeni.ResumoCartao[]>([]);

    const loadResumoCartao = async (p: string) => {
        try {
            const data = await dashBoradService.getResumoCartao(p);
            if (data) {
                setResumoCartao(data);
            }
        } catch (error) {
            console.error("Erro ao carregar resumo de cartões", error);
        }
    };

    useEffect(() => {
        (async function loadData() {
            const [cartaoData] = await Promise.all([cartaoService.getAll()]);
            setEntities(cartaoData);
        })();
        if (entities.length === 0) {
            setRefresh(true);
        }
    }, [entities.length, setEntities, setRefresh, refresh]);

    useEffect(() => {
        loadResumoCartao(periodo);
    }, [periodo, refresh, entities]);

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
        return <RightToolbarTemplate exportCSV={exportCSV} />;
    };

    const surnameBodyTemplate = (rowData: Zeni.Card) => {
        return <GenericBodyTemplate title={"apelido"} value={rowData.apelido} />;
    };

    const quantityBodyTemplate = (rowData: Zeni.Card) => {
        return <GenericBodyTemplate title={"quantidadeCompras"} value={rowData.quantidadeCompras} />;
    };

    const expenseBodyTemplate = (rowData: Zeni.Card) => {
        return <GenericBodyTemplate title={"gastos"} value={formatCurrency(rowData.gastos)} />;
    };

    const finalNumberBodyTemplate = (rowData: Zeni.Card) => {
        return <GenericBodyTemplate title={"ultimosDigitos"} value={rowData.ultimosDigitos} />;
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
            <h5 className="m-0">Meus Cartões</h5>
            <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search" />
                <InputText type="search" onInput={(e) => setGlobalFilter(e.currentTarget.value)} placeholder="Pesquisar..." />
            </span>
        </div>
    );

    const saving = () => {
        saveEntity(cartaoService).then(() => {
            setRefresh(prev => !prev);
        });
    };

    const deleteById = () => {
        deleteEntityById(cartaoService).then(() => {
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

    const deleteUsersDialogFooter = (
        <>
            <Button label="Não" icon="pi pi-times" text onClick={hideDeleteEntitiesDialog} />
            <Button label="Sim" icon="pi pi-check" text onClick={deleteSelectedEntities} />
        </>
    );

    // Calculate period totals
    const totalGastoPeriodo = useMemo(() => {
        return resumoCartao.reduce((acc, curr) => acc + curr.valorTotal, 0);
    }, [resumoCartao]);

    return (
        <div className="grid crud-demo">
            <div className="col-12">
                <Toast ref={toast} />

                {/* Resumo e Filtro de Gastos do Cartão por Período */}
                <div className="card mb-4">
                    <div className="flex flex-column md:flex-row justify-content-between align-items-start md:align-items-center mb-4 gap-3">
                        <div>
                            <h4 className="m-0 font-semibold text-900">Análise de Gastos dos Cartões</h4>
                            <p className="text-600 text-sm mt-1 m-0">Visualize o resumo das despesas agrupadas por cartão e período</p>
                        </div>
                        <div className="flex gap-2">
                            <Button label="Geral" onClick={() => setPeriodo('GERAL')} className={periodo === 'GERAL' ? 'p-button-sm' : 'p-button-sm p-button-outlined'} />
                            <Button label="Semanal" onClick={() => setPeriodo('SEMANAL')} className={periodo === 'SEMANAL' ? 'p-button-sm' : 'p-button-sm p-button-outlined'} />
                            <Button label="Mensal" onClick={() => setPeriodo('MENSAL')} className={periodo === 'MENSAL' ? 'p-button-sm' : 'p-button-sm p-button-outlined'} />
                            <Button label="Anual" onClick={() => setPeriodo('ANUAL')} className={periodo === 'ANUAL' ? 'p-button-sm' : 'p-button-sm p-button-outlined'} />
                        </div>
                    </div>

                    <div className="grid">
                        <div className="col-12 lg:col-4">
                            <div className="p-3 border-round border-1 surface-border bg-card flex flex-column h-full justify-content-center">
                                <span className="text-500 font-medium mb-2">Gasto Total no Período</span>
                                <div className="text-900 font-bold text-3xl">
                                    {new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(totalGastoPeriodo)}
                                </div>
                                <span className="text-green-500 font-medium mt-2">
                                    {resumoCartao.length} {resumoCartao.length === 1 ? 'cartão ativo' : 'cartões ativos'}
                                </span>
                            </div>
                        </div>

                        <div className="col-12 lg:col-8">
                            <div className="p-3 border-round border-1 surface-border bg-card h-full">
                                <span className="text-500 font-medium mb-3 block">Detalhamento por Cartão</span>
                                {resumoCartao.length === 0 ? (
                                    <div className="text-600 text-center py-3">Sem gastos registrados neste período.</div>
                                ) : (
                                    <div className="flex flex-column gap-3">
                                        {resumoCartao.map((item, idx) => {
                                            const colors = ['bg-blue-500', 'bg-purple-500', 'bg-orange-500', 'bg-green-500'];
                                            const color = colors[idx % colors.length];
                                            return (
                                                <div key={idx} className="flex flex-column">
                                                    <div className="flex justify-content-between mb-1 text-sm font-medium">
                                                        <span className="text-800">{item.apelidoCartao} (**** {item.ultimosDigitos})</span>
                                                        <span className="text-900">{new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(item.valorTotal)} ({item.porcentagem.toFixed(0)}%)</span>
                                                    </div>
                                                    <div className="surface-200 border-round w-full" style={{ height: '6px' }}>
                                                        <div className={`${color} border-round`} style={{ width: `${item.porcentagem}%`, height: '100%' }}></div>
                                                    </div>
                                                </div>
                                            );
                                        })}
                                    </div>
                                )}
                            </div>
                        </div>
                    </div>
                </div>

                {/* Tabela Principal */}
                <div className="card">
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
                        currentPageReportTemplate="Exibindo {first} a {last} de {totalRecords} registros"
                        globalFilter={globalFilter}
                        emptyMessage="Nenhum cartão encontrado."
                        header={header}
                    >
                        <Column selectionMode="multiple" headerStyle={{ width: '4rem' }}></Column>
                        <Column field="surname" header="Apelido" sortable body={surnameBodyTemplate} headerStyle={{ minWidth: '12rem' }}></Column>
                        <Column field="expesne" header="Gastos Totais (Histórico)" body={expenseBodyTemplate} sortable headerStyle={{ minWidth: '15rem' }}></Column>
                        <Column field="quantity" header="Compras Totais (Histórico)" sortable body={quantityBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
                        <Column field="finalNumber" header="Digitos" sortable body={finalNumberBodyTemplate} headerStyle={{ minWidth: '8rem' }}></Column>
                        <Column body={actionBodyTemplate} headerStyle={{ minWidth: '10rem' }}></Column>
                    </DataTable>

                    {/* Dialogos */}
                    <Dialog visible={entityDialog} style={{ width: '400px' }} header="Detalhes do Cartão" modal className="p-fluid" footer={userDialogFooter} onHide={hideDialog}>
                        <div className="field">
                            {submitted && !entity.apelido && <small className="p-error block">Campo obrigatório.</small>}
                        </div>

                        <div className="field">
                            <label htmlFor="apelido">Apelido</label>
                            <InputText
                                id="apelido"
                                value={entity.apelido}
                                onChange={(e) => onInputChange(e, 'apelido')}
                                required
                                autoFocus
                                className={classNames({ 'p-invalid': submitted && !entity.apelido })}
                            />
                        </div>

                        <div className="field">
                            <label htmlFor="ultimosDigitos">Últimos 4 Dígitos</label>
                            <InputText
                                id="ultimosDigitos"
                                value={entity.ultimosDigitos}
                                onChange={(e) => onInputChange(e, 'ultimosDigitos')}
                                required
                                maxLength={4}
                                className={classNames({ 'p-invalid': submitted && !entity.ultimosDigitos })}
                            />
                        </div>
                    </Dialog>

                    <Dialog visible={deleteEntityDialog} style={{ width: '450px' }} header="Confirmação" modal footer={deleteEntityDialogFooter} onHide={hideDeleteDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {entity && (
                                <span>
                                    Você realmente deseja excluir o cartão <b>{entity.apelido}</b>?
                                </span>
                            )}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteEntitiesDialog} style={{ width: '450px' }} header="Confirmação" modal footer={deleteUsersDialogFooter} onHide={hideDeleteEntitiesDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {entity && <span>Você realmente deseja excluir os cartões selecionados?</span>}
                        </div>
                    </Dialog>
                </div>
            </div>
        </div>
    );
};

export default CardsPage;
