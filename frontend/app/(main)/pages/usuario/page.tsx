// /* eslint-disable @next/next/no-img-element */
// 'use client';
// import { Button } from 'primereact/button';
// import { Column } from 'primereact/column';
// import { DataTable } from 'primereact/datatable';
// import { Dialog } from 'primereact/dialog';
// import { FileUpload } from 'primereact/fileupload';
// import { InputNumber, InputNumberValueChangeEvent } from 'primereact/inputnumber';
// import { InputText } from 'primereact/inputtext';
// import { InputTextarea } from 'primereact/inputtextarea';
// import { RadioButton, RadioButtonChangeEvent } from 'primereact/radiobutton';
// import { Rating } from 'primereact/rating';
// import { Toast } from 'primereact/toast';
// import { Toolbar } from 'primereact/toolbar';
// import { classNames } from 'primereact/utils';
// import React, { use, useEffect, useMemo, useRef, useState } from 'react';
// import { ProductService } from '../../../../demo/service/ProductService';
// import { UserService } from '@/service/UsuarioService';
// //import { Project } from '@/types';
//
// /* @todo Used 'as any' for types here. Will fix in next version due to onSelectionChange event type issue. */
// const Crud = () => {
//     let emptyUser: Project.User = {
//         id: 0,
//         name: '',
//         email: '',
//         login: '',
//         password: ''
//     };
//
//     const [users, setUsers] = useState<Project.User[]>([]);
//     const [userDialog, setUserDialog] = useState(false);
//     const [deleteUserDialog, setDeleteUserDialog] = useState(false);
//     const [deleteUsersDialog, setDeleteUsersDialog] = useState(false);
//     const [user, setUser] = useState<Project.User>(emptyUser);
//     const [selectedUsers, setSelectedUsers] = useState<Project.User[]>([]);
//     const [submitted, setSubmitted] = useState(false);
//     const [globalFilter, setGlobalFilter] = useState('');
//     const toast = useRef<Toast>(null);
//     const dt = useRef<DataTable<any>>(null);
//     const userService = useMemo(() => new UserService(), []);
//     const [refresh, setRefresh] = useState(false);
//
//     useEffect(() => {
//         //ProductService.getProducts().then((data) => setProducts(data as any));
//     if(users.length === 0){
//         userService.getAll()
//         .then((response) => {
//          setUsers(response.data);
//          console.log(response.data);
//           })
//           .catch((error) => {
//             console.error('There was an error!', error);
//           });
//     }
//
//     }, [refresh,userService]);
//
//     const formatCurrency = (value: number) => {
//         return value.toLocaleString('en-US', {
//             style: 'currency',
//             currency: 'USD'
//         });
//     };
//
//     const openNew = () => {
//         setUser(emptyUser);
//         setSubmitted(false);
//         setUserDialog(true);
//     };
//
//     const hideDialog = () => {
//         setSubmitted(false);
//         setUserDialog(false);
//     };
//
//     const hideDeleteUserDialog = () => {
//         setDeleteUserDialog(false);
//     };
//
//     const hideDeleteUsersDialog = () => {
//         setDeleteUsersDialog(false);
//     };
//
//     const saveUser = () => {
//         setSubmitted(true);
//
//         if(!user.id){
//             userService.create(user)
//             .then((response) => {
//                 setUserDialog(false);
//                 setUser(emptyUser);
//                 setUsers([]);
//                 toast.current?.show({
//                     severity: 'success',
//                     summary: 'Successful',
//                     detail: 'User Created',
//                 });
//                  setRefresh(prev => !prev);
//
//             }).catch((error) => {
//                 console.error('There was an error!', error);
//                 toast.current?.show({
//                     severity: 'error',
//                     summary: 'Error',
//                     detail: 'Erro ao criar usuario: ' + error,
//                 });
//             });
//
//         }
//         else {
//             userService.update(user)
//             .then((response) => {
//                 setUserDialog(false);
//                 setUser(emptyUser);
//                 setUsers([]);
//                 toast.current?.show({
//                     severity: 'success',
//                     summary: 'Successful',
//                     detail: 'User Updated',
//                 });
//                  setRefresh(prev => !prev);
//             }).catch((error) => {
//                 console.error('There was an error!', error);
//                 toast.current?.show({
//                     severity: 'error',
//                     summary: 'Error',
//                     detail: 'Erro ao atualizar usuario: ' + error,
//                 });
//             });
//         }
//
//     };
//
//     const editUser = (user: Project.User) => {
//         setUser({ ...user });
//         setUserDialog(true);
//     };
//
//     const confirmDeleteUser = (user: Project.User) => {
//         setUser(user);
//         setDeleteUserDialog(true);
//     };
//
//     const deleteUser = () => {
//         userService.delete(user.id as number)
//         .then((response) => {
//             console.log(response.data);
//             setDeleteUserDialog(false);
//             setUser(emptyUser);
//             setUsers([]);
//             toast.current?.show({
//                 severity: 'success',
//                 summary: 'Successful',
//                 detail: 'User Deleted',
//             });
//              setRefresh(prev => !prev);
//         }).catch((error) => {
//             console.error('There was an error!', error);
//             toast.current?.show({
//                 severity: 'error',
//                 summary: 'Error',
//                 detail: 'Erro ao excluir usuario: ' + error,
//             });
//
//         });
//
//     };
//
//     const findIndexById = (id: number): number => {
//         let index = -1;
//         for (let i = 0; i < (users as any)?.length; i++) {
//             if ((users as any)[i].id === id) {
//                 index = i;
//                 break;
//             }
//         }
//
//         return index;
//     };
//
//     // const createId = () => {
//     //     let id = '';
//     //     let chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
//     //     for (let i = 0; i < 5; i++) {
//     //         id += chars.charAt(Math.floor(Math.random() * chars.length));
//     //     }
//     //     return id;
//     // };
//
//     const exportCSV = () => {
//         dt.current?.exportCSV();
//     };
//
//     const confirmDeleteSelected = () => {
//         setDeleteUsersDialog(true);
//     };
//
//     const deleteSelectedUsers = () => {
//         Promise.all(selectedUsers.map(async(_user) =>{
//             if(_user.id){
//                 await userService.delete(_user.id)
//             }
//         }))
//         .then((response) => {
//             setUsers([]);
//             setSelectedUsers([]);
//             setDeleteUsersDialog(false);
//             toast.current?.show({
//                 severity: 'success',
//                 summary: 'Successful',
//                 detail: 'Users Deleted',
//             });
//              setRefresh(prev => !prev);
//         }).catch((error) => {
//             console.error('There was an error!', error);
//             toast.current?.show({
//                 severity: 'error',
//                 summary: 'Error',
//                 detail: 'Erro ao excluir usuarios: ' + error,
//             });
//         });
//     };
//
//     const onCategoryChange = (e: RadioButtonChangeEvent) => {
//         let _user = { ...user };
//         //_user['category'] = e.value;
//         setUser(prev => ({ ...prev, ['category']: e.value }));
//     };
//
//     const onInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>, name: string) => {
//         const val = (e.target && e.target.value) || '';
//         setUser(prev => ({ ...prev, [name]: val }));
//     };
//
//     const onInputNumberChange = (e: InputNumberValueChangeEvent, name: string) => {
//         const val = e.value || 0;
//         setUser(prev => ({ ...prev, [name]: val }));
//     };
//
//     const leftToolbarTemplate = () => {
//         return (
//             <React.Fragment>
//                 <div className="my-2">
//                     <Button label="Novo" icon="pi pi-plus" severity="success" className=" mr-2" onClick={openNew} />
//                     <Button label="Excluir" icon="pi pi-trash" severity="danger" onClick={confirmDeleteSelected} disabled={!selectedUsers || !(selectedUsers as any).length} />
//                 </div>
//             </React.Fragment>
//         );
//     };
//
//     const rightToolbarTemplate = () => {
//         return (
//             <React.Fragment>
//                 <FileUpload mode="basic" accept="image/*" maxFileSize={1000000} chooseLabel="Import" className="mr-2 inline-block" />
//                 <Button label="Export" icon="pi pi-upload" severity="help" onClick={exportCSV} />
//             </React.Fragment>
//         );
//     };
//
//     const codeBodyTemplate = (rowData: Project.User) => {
//         return (
//             <>
//                 <span className="p-column-title">code</span>
//                 {rowData.id}
//             </>
//         );
//     };
//
//     const nameBodyTemplate = (rowData: Project.User) => {
//         return (
//             <>
//                 <span className="p-column-title">Name</span>
//                 {rowData.name}
//             </>
//         );
//     };
//
//     const emailBodyTemplate = (rowData: Project.User) => {
//         return (
//             <>
//                 <span className="p-column-title">Email</span>
//                 {rowData.email}
//             </>
//         );
//     };
//
//
//       const loginBodyTemplate = (rowData: Project.User) => {
//         return (
//             <>
//                 <span className="p-column-title">Login</span>
//                 {rowData.login}
//             </>
//         );
//     };
//
//
//
//     // const imageBodyTemplate = (rowData: Project.User) => {
//     //     return (
//     //         <>
//     //             <span className="p-column-title">Image</span>
//     //             <img src={`/demo/images/product/${rowData.image}`} alt={rowData.image} className="shadow-2" width="100" />
//     //         </>
//     //     );
//     // };
//
//     // const priceBodyTemplate = (rowData: Project.User) => {
//     //     return (
//     //         <>
//     //             <span className="p-column-title">Price</span>
//     //             {formatCurrency(rowData.price as number)}
//     //         </>
//     //     );
//     // };
//
//     // const categoryBodyTemplate = (rowData: Project.User) => {
//     //     return (
//     //         <>
//     //             <span className="p-column-title">email</span>
//     //             {rowData.email}
//     //         </>
//     //     );
//     // };
//
//     // const ratingBodyTemplate = (rowData: Project.User) => {
//     //     return (
//     //         <>
//     //             <span className="p-column-title">Reviews</span>
//     //             <Rating value={rowData.id} readOnly cancel={false} />
//     //         </>
//     //     );
//     // };
//
//     // const statusBodyTemplate = (rowData: Project.User) => {
//     //     return (
//     //         <>
//     //             <span className="p-column-title">Status</span>
//     //             <span className={`product-badge status-${rowData.inventoryStatus?.toLowerCase()}`}>{rowData.inventoryStatus}</span>
//     //         </>
//     //     );
//     // };
//
//     const actionBodyTemplate = (rowData: Project.User) => {
//         return (
//             <>
//                 <Button icon="pi pi-pencil" rounded severity="success" className="mr-2" onClick={() => editUser(rowData)} />
//                 <Button icon="pi pi-trash" rounded severity="warning" onClick={() => confirmDeleteUser(rowData)} />
//             </>
//         );
//     };
//
//     const header = (
//         <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
//             <h5 className="m-0">Manage User</h5>
//             <span className="block mt-2 md:mt-0 p-input-icon-left">
//                 <i className="pi pi-search" />
//                 <InputText type="search" onInput={(e) => setGlobalFilter(e.currentTarget.value)} placeholder="Search..." />
//             </span>
//         </div>
//     );
//
//     const userDialogFooter = (
//         <>
//             <Button label="Cancelar" icon="pi pi-times" text onClick={hideDialog} />
//             <Button label="Salvar" icon="pi pi-check" text onClick={saveUser} />
//         </>
//     );
//     const deleteUserDialogFooter = (
//         <>
//             <Button label="Não" icon="pi pi-times" text onClick={hideDeleteUserDialog} />
//             <Button label="Sim" icon="pi pi-check" text onClick={deleteUser} />
//         </>
//     );
//     const deleteUsersDialogFooter = (
//         <>
//             <Button label="Não" icon="pi pi-times" text onClick={hideDeleteUsersDialog} />
//             <Button label="Yes" icon="pi pi-check" text onClick={deleteSelectedUsers} />
//         </>
//     );
//
//     return (
//         <div className="grid crud-demo">
//             <div className="col-12">
//                 <div className="card">
//                     <Toast ref={toast} />
//                     <Toolbar className="mb-4" left={leftToolbarTemplate} right={rightToolbarTemplate}></Toolbar>
//
//                     <DataTable
//                         ref={dt}
//                         value={users}
//                         selection={selectedUsers}
//                         onSelectionChange={(e) => setSelectedUsers(e.value as any)}
//                         dataKey="id"
//                         paginator
//                         rows={10}
//                         rowsPerPageOptions={[5, 10, 25]}
//                         className="datatable-responsive"
//                         paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
//                         currentPageReportTemplate="Showing {first} to {last} of {totalRecords} products"
//                         globalFilter={globalFilter}
//                         emptyMessage="No products found."
//                         header={header}
//                         responsiveLayout="scroll"
//                     >
//                         <Column selectionMode="multiple" headerStyle={{ width: '4rem' }}></Column>
//                         <Column field="code" header="Code" sortable body={codeBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
//                         <Column field="name" header="Name" sortable body={nameBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
//                         <Column field="email" header="Email" sortable body={emailBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
//                         <Column field="login" header="Login" sortable body={loginBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
//                         <Column body={actionBodyTemplate} headerStyle={{ minWidth: '10rem' }}></Column>
//                     </DataTable>
//
//                     <Dialog visible={userDialog} style={{ width: '450px' }} header="Detalhes Usuario" modal className="p-fluid" footer={userDialogFooter} onHide={hideDialog}>
//
//                         <div className="field">
//                             {submitted && !user.id && <small className="p-invalid">Name is required.</small>}
//                         </div>
//                         <div className="field">
//                             <label htmlFor="description">Nome</label>
//                             <InputText
//                                 id="nome"
//                                 value={user.name}
//                                 onChange={(e) => onInputChange(e, 'name')}
//                                 required
//                                 autoFocus
//                                 className={classNames({
//                                     'p-invalid': submitted && !user.name
//                                 })}
//                             />
//                         </div>
//                          <div className="field">
//                             <label htmlFor="description">Login</label>
//                             <InputText
//                                 id="login"
//                                 value={user.login}
//                                 onChange={(e) => onInputChange(e, 'login')}
//                                 required
//                                 autoFocus
//                                 className={classNames({
//                                     'p-invalid': submitted && !user.login
//                                 })}
//                             />
//                         </div> <div className="field">
//                             <label htmlFor="description">Email</label>
//                             <InputText
//                                 id="email"
//                                 value={user.email}
//                                 onChange={(e) => onInputChange(e, 'email')}
//                                 required
//                                 autoFocus
//                                 className={classNames({
//                                     'p-invalid': submitted && !user.email
//                                 })}
//                             />
//                         </div>
//                         <div className="field">
//                             <label htmlFor="senha">Senha</label>
//                             <InputText
//                                 id="password"
//                                 value={user.password}
//                                 onChange={(e) => onInputChange(e, 'password')}
//                                 required
//                                 autoFocus
//                                 className={classNames({
//                                     'p-invalid': submitted && !user.password
//                                 })}
//                             />
//                         </div>
//
//                     </Dialog>
//
//                     <Dialog visible={deleteUserDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteUserDialogFooter} onHide={hideDeleteUserDialog}>
//                         <div className="flex align-items-center justify-content-center">
//                             <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
//                             {user && (
//                                 <span>
//                                     Voce Realmente Deseja excluir o usuario <b>{user.name}</b>?
//                                 </span>
//                             )}
//                         </div>
//                     </Dialog>
//
//                     <Dialog visible={deleteUsersDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteUsersDialogFooter} onHide={hideDeleteUserDialog}>
//                         <div className="flex align-items-center justify-content-center">
//                             <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
//                             {user && <span>Voce Realmente Deseja excluir o usuario ?</span>}
//                         </div>
//                     </Dialog>
//                 </div>
//             </div>
//         </div>
//     );
// };
//
//
// export default Crud;
