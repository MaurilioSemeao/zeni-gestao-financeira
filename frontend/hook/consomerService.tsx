/* eslint-disable @next/next/no-img-element */
'use client'
import { useCrud } from '@/hook/useEntityCrud';
import { useEffect, useMemo } from 'react';
import { transactionService } from '@/service/TransactionService';



export const useGetService =() =>{

    let emptyTransaction: Zeni.Transaction = {
        id: 0,
        descricao: '',
        valor: 0,
        tipo: '',
        meioPagamento: '',
        dataTransacao: '',
        categoriaId: 0,
        cartaoId: null,
        contaId: null,
        categoria: null
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


    } = useCrud<Zeni.Transaction>(emptyTransaction)





    useEffect(() => {
        //ProductService.getProducts().then((data) => setProducts(data as any));
        if(entities.length === 0){
            transactionService.getAll()
                .then((response) => {
                    setEntities(response.data);
                })
                .catch((error) => {
                    console.error('There was an error!', error);
                });
        }

    }, [entities.length,  setEntities, transactionService]);
}
