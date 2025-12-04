import { useRef, useState } from 'react';
import { Toast } from 'primereact/toast';
import { DataTable } from 'primereact/datatable';
import { BaseService } from '@/service/BaseService';


export function useCrud<T extends { id: number }>(emptyEntity: T) {
    const [entities, setEntities] = useState<T[]>([]);
    const [entityDialog, setEntityDialog] = useState(false);
    const [deleteEntityDialog, setDeleteEntityDialog] = useState(false);
    const [deleteEntitiesDialog, setDeleteEntitiesDialog] = useState(false);
    const [entity, setEntity] = useState<T>(emptyEntity);
    const [selectedEntities, setSelectedEntities] = useState<T[] >([]);
    const [submitted, setSubmitted] = useState(false);
    const [globalFilter, setGlobalFilter] = useState('');
    const toast = useRef<Toast>(null);
    const dt = useRef<DataTable<any>>(null);

    const openNew = () => {
        setEntity(emptyEntity);
        setSubmitted(false);
        setEntityDialog(true);
    };

    const hideDialog = () => {
        setSubmitted(false);
        setEntityDialog(false);
    };

    const hideDeleteDialog = () => setDeleteEntityDialog(false);


    const hideDeleteEntitiesDialog = () => setDeleteEntitiesDialog(false);




    const saveEntity = async (service: BaseService) => {
        setSubmitted(true);
        console.log('entity', entity);
        try {
            if (!entity.id) {
                await service.create(entity);
                toast.current?.show({
                    severity: 'success',
                    summary: 'Sucesso',
                    detail: 'Registro criado com sucesso!',
                    life: 3000,
                });
            } else  {

                await service.update(entity);
                toast.current?.show({
                    severity: 'success',
                    summary: 'Sucesso',
                    detail: 'Registro atualizado com sucesso!',
                    life: 3000,
                });
            }


            setEntityDialog(false);
            setEntity(emptyEntity);
            setEntities([]);
        } catch (error) {
            console.error('Erro ao salvar entidade:', error);
            toast.current?.show({
                severity: 'error',
                summary: 'Erro',
                detail: `Erro ao salvar registro: ${error}`,
                life: 3000,
            });
        }
    };


    const editEntity = (item: T) => {
        setEntity({ ...item });
        setEntityDialog(true);
    };

    const confirmDeleteEntity = (item: T) => {
        setEntity(item);
        setDeleteEntityDialog(true);
    };

    const deleteEntityById = async (service: BaseService) => {
        setSubmitted(true);
       console.log('deleteEntityById', entity);
       try {
           if(entity.id) {
               await service.delete(entity.id);
               toast.current?.show({
                   severity: 'success',
                   summary: 'Removido',
                   detail: `Registro excluído com sucesso`,
                   life: 3000,
               });
           }

           setDeleteEntityDialog(false);
           setEntity(emptyEntity);
           setEntities([]);
       }
       catch (error) {
           console.error('Erro ao Deletar registro:', error);
           toast.current?.show({
               severity: 'error',
               summary: 'Erro',
               detail: `Erro ao deletar Registro ${error}`,
           })
       }

    };

    // const deleteEntityById = () => {
    //     const _entities = entities.filter((e) => e.id !== entity.id);
    //     setEntities(_entities);
    //     setDeleteEntityDialog(false);
    //     setEntity(emptyEntity);
    //     toast.current?.show({
    //         severity: 'success',
    //         summary: 'Removido',
    //         detail: 'Registro excluído com sucesso',
    //         life: 3000,
    //     });
    // };

    const confirmDeleteSelected = () => {
        setDeleteEntitiesDialog(true);
    };

    const deleteSelectedEntities = () => {
        const _entities = entities.filter((e) => !selectedEntities?.includes(e));
        setEntities(_entities);
        setDeleteEntitiesDialog(false);
        setSelectedEntities([]);
        toast.current?.show({
            severity: 'success',
            summary: 'Removidos',
            detail: 'Registros excluídos com sucesso',
            life: 3000,
        });
    };

    const exportCSV = () => {
        dt.current?.exportCSV();
    };

    const formatCurrency = (value: number) => {
        return value.toLocaleString('pt-BR', {
            style: 'currency',
            currency: 'BRL'
        });
    };


    // const deleteUser = () => {
    //     userService.delete(user.id as number)
    //     .then((response) => {
    //         console.log(response.data);
    //         setDeleteUserDialog(false);
    //         setUser(emptyUser);
    //         setUsers([]);
    //         toast.current?.show({
    //             severity: 'success',
    //             summary: 'Successful',
    //             detail: 'User Deleted',
    //         });
    //          setRefresh(prev => !prev);
    //     }).catch((error) => {
    //         console.error('There was an error!', error);
    //         toast.current?.show({
    //             severity: 'error',
    //             summary: 'Error',
    //             detail: 'Erro ao excluir usuario: ' + error,
    //         });
    //
    //     });
    //
    // };




    // const deleteSelectedUsers = () => {
    //     Promise.all(selectedUsers.map(async(_user) =>{
    //         if(_user.id){
    //             await userService.delete(_user.id)
    //         }
    //     }))
    //     .then((response) => {
    //         setUsers([]);
    //         setSelectedUsers([]);
    //         setDeleteUsersDialog(false);
    //         toast.current?.show({
    //             severity: 'success',
    //             summary: 'Successful',
    //             detail: 'Users Deleted',
    //         });
    //          setRefresh(prev => !prev);
    //     }).catch((error) => {
    //         console.error('There was an error!', error);
    //         toast.current?.show({
    //             severity: 'error',
    //             summary: 'Error',
    //             detail: 'Erro ao excluir usuarios: ' + error,
    //         });
    //     });
    // };

    return {
        entities,
        setEntities,
        entity,
        setEntity,
        entityDialog,
        setEntityDialog,
        deleteEntityDialog,
        setDeleteEntityDialog,
        deleteEntitiesDialog,
        setDeleteEntitiesDialog,
        selectedEntities,
        setSelectedEntities,
        submitted,
        setSubmitted,
        globalFilter,
        setGlobalFilter,
        toast,
        dt,

        // Funções utilitárias
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
    };
}

