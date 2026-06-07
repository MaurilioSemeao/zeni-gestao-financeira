/* eslint-disable @next/next/no-img-element */
'use client';
import { Button } from 'primereact/button';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { Toast } from 'primereact/toast';
import { Toolbar } from 'primereact/toolbar';
import { Tag } from 'primereact/tag';
import { classNames } from 'primereact/utils';
import React, { useEffect, useMemo, useState } from 'react';
import { useCrud } from '@/hook/useEntityCrud';
import { createFormHandlers } from '@/utils/formHandlers';
import { LeftToolbarTemplate } from '@/app/(main)/components/Templates/LeftToolbarTemplate';
import { RightToolbarTemplate } from '@/app/(main)/components/Templates/RightToolbarTemplate';
import { GenericBodyTemplate } from '@/app/(main)/components/Templates/GenericBodyTemplate';

import { categoriaService } from '@/service/CategoriaService';
import { dashBoradService } from '@/service/DashBoardService';

const CategoryPage = () => {
    const emptyCategory: Zeni.Category = {
        id: 0,
        nome: '',
        padrao: false
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
    } = useCrud<Zeni.Category>(emptyCategory);

    const { onInputChange } = createFormHandlers<Zeni.Category>(setEntity);

    const [refresh, setRefresh] = useState(false);
    const [periodo, setPeriodo] = useState<string>('MENSAL');
    const [resumoCategoria, setResumoCategoria] = useState<Zeni.ResumoCategoria[]>([]);

    const loadResumoCategoria = async (p: string) => {
        try {
            const data = await dashBoradService.getResumoCategoria(p);
            if (data) {
                setResumoCategoria(data);
            }
        } catch (error) {
            console.error("Erro ao buscar resumo de despesas por categoria", error);
        }
    };

    useEffect(() => {
        (async function loadData() {
            const data = await categoriaService.getAll();
            setEntities(data);
        })();
    }, [setEntities, refresh]);

    useEffect(() => {
        loadResumoCategoria(periodo);
    }, [periodo, refresh, entities]);

    const leftToolbarTemplate = () => {
        return (
            <LeftToolbarTemplate<Zeni.Category>
                openNew={openNew}
                confirmDeleteSelected={confirmDeleteSelected}
                selectedGeneric={selectedEntities}
            />
        );
    };

    const rightToolbarTemplate = () => {
        return <RightToolbarTemplate exportCSV={exportCSV} />;
    };

    const nameBodyTemplate = (rowData: Zeni.Category) => {
        return <GenericBodyTemplate title={"nome"} value={rowData.nome} />;
    };

    const typeBodyTemplate = (rowData: Zeni.Category) => {
        return rowData.padrao ? (
            <Tag value="Sistema (Padrão)" severity="info" />
        ) : (
            <Tag value="Personalizada" severity="success" />
        );
    };

    const actionBodyTemplate = (rowData: Zeni.Category) => {
        if (rowData.padrao) {
            return (
                <div className="flex gap-2">
                    <span className="text-500 text-sm font-medium italic">Ações desabilitadas</span>
                </div>
            );
        }
        return (
            <>
                <Button icon="pi pi-pencil" rounded severity="success" className="mr-2" onClick={() => editEntity(rowData)} />
                <Button icon="pi pi-trash" rounded severity="warning" onClick={() => confirmDeleteEntity(rowData)} />
            </>
        );
    };

    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">Categorias de Gasto</h5>
            <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search" />
                <InputText type="search" onInput={(e) => setGlobalFilter(e.currentTarget.value)} placeholder="Pesquisar..." />
            </span>
        </div>
    );

    const saving = () => {
        saveEntity(categoriaService).then(() => {
            setRefresh(prev => !prev);
        });
    };

    const deleteById = () => {
        deleteEntityById(categoriaService).then(() => {
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

    // Calculate total expenses for the visual overview
    const totalGastoPeriodo = useMemo(() => {
        return resumoCategoria.reduce((acc, curr) => acc + curr.valorTotal, 0);
    }, [resumoCategoria]);

    // Row selection filter: prevent selecting standard categories
    const isRowSelectable = (event: any) => {
        return !event.data.padrao;
    };

    return (
        <div className="grid crud-demo">
            <div className="col-12">
                <Toast ref={toast} />

                {/* Resumo de Gastos por Categoria (Topo) */}
                <div className="card mb-4">
                    <div className="flex flex-column md:flex-row justify-content-between align-items-start md:align-items-center mb-4 gap-3">
                        <div>
                            <h4 className="m-0 font-semibold text-900">Análise de Gastos por Categoria</h4>
                            <p className="text-600 text-sm mt-1 m-0">Consulte o resumo das suas despesas agrupadas por categoria de gasto</p>
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
                                <span className="text-500 font-medium mb-2">Despesa Total do Período</span>
                                <div className="text-900 font-bold text-3xl">
                                    {new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(totalGastoPeriodo)}
                                </div>
                                <span className="text-blue-500 font-medium mt-2">
                                    {resumoCategoria.filter(c => c.valorTotal > 0).length} categorias com movimentações
                                </span>
                            </div>
                        </div>

                        <div className="col-12 lg:col-8">
                            <div className="p-3 border-round border-1 surface-border bg-card h-full">
                                <span className="text-500 font-medium mb-3 block">Gastos por Categoria</span>
                                {resumoCategoria.length === 0 ? (
                                    <div className="text-600 text-center py-3">Sem gastos registrados neste período.</div>
                                ) : (
                                    <div className="flex flex-column gap-3" style={{ maxHeight: '180px', overflowY: 'auto' }}>
                                        {resumoCategoria.map((item, idx) => {
                                            const colors = ['bg-blue-500', 'bg-purple-500', 'bg-orange-500', 'bg-green-500', 'bg-cyan-500', 'bg-pink-500'];
                                            const color = colors[idx % colors.length];
                                            if (item.valorTotal <= 0) return null;
                                            return (
                                                <div key={idx} className="flex flex-column">
                                                    <div className="flex justify-content-between mb-1 text-sm font-medium">
                                                        <span className="text-800">{item.nomeCategoria}</span>
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

                {/* CRUD Categorias */}
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
                        emptyMessage="Nenhuma categoria encontrada."
                        header={header}
                        isDataSelectable={isRowSelectable}
                    >
                        <Column selectionMode="multiple" headerStyle={{ width: '4rem' }}></Column>
                        <Column field="nome" header="Nome" sortable body={nameBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
                        <Column field="padrao" header="Tipo de Categoria" sortable body={typeBodyTemplate} headerStyle={{ minWidth: '12rem' }}></Column>
                        <Column body={actionBodyTemplate} headerStyle={{ minWidth: '10rem' }}></Column>
                    </DataTable>

                    {/* Diálogo de Criação/Edição */}
                    <Dialog visible={entityDialog} style={{ width: '400px' }} header="Detalhes da Categoria" modal className="p-fluid" footer={userDialogFooter} onHide={hideDialog}>
                        <div className="field">
                            {submitted && !entity.nome && <small className="p-error block">Campo obrigatório.</small>}
                        </div>

                        <div className="field">
                            <label htmlFor="nome">Nome da Categoria</label>
                            <InputText
                                id="nome"
                                value={entity.nome}
                                onChange={(e) => onInputChange(e, 'nome')}
                                required
                                autoFocus
                                className={classNames({ 'p-invalid': submitted && !entity.nome })}
                            />
                        </div>
                    </Dialog>

                    {/* Diálogos de Exclusão */}
                    <Dialog visible={deleteEntityDialog} style={{ width: '450px' }} header="Confirmação" modal footer={deleteEntityDialogFooter} onHide={hideDeleteDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {entity && (
                                <span>
                                    Você realmente deseja excluir a categoria <b>{entity.nome}</b>?
                                </span>
                            )}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteEntitiesDialog} style={{ width: '450px' }} header="Confirmação" modal footer={deleteEntitiesDialogFooter} onHide={hideDeleteEntitiesDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {entity && <span>Você realmente deseja excluir as categorias selecionadas?</span>}
                        </div>
                    </Dialog>
                </div>
            </div>
        </div>
    );
};

export default CategoryPage;
