/* eslint-disable @next/next/no-img-element */
'use client'
import { useCrud } from '@/hook/useEntityCrud';
import { useEffect, useMemo } from 'react';
import { TransactionService } from '@/service/TransactionService';



export const useGetService =() =>{

    let emptyTransaction: Zeni.Transaction = {
        id: 0,
        description: '',
        price: 0,
        data: ''
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


    const transactionService =useMemo(() => new TransactionService(),[])


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
