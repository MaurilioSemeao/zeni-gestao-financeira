import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import React from 'react';
import { GenericBodyTemplate } from '@/app/(main)/components/Templates/GenericBodyTemplate';
import { useCrud } from '@/hook/useEntityCrud';


export const CardRecentTransaction = (iTransaction:Zeni.Transaction[])=> {
    let emptyTransaction: Zeni.Transaction = {
        id: 0,
        description: '',
        price: 0,
        data: ''
    };

   const tCrud = useCrud<Zeni.Transaction>(emptyTransaction)

    tCrud.setEntities(iTransaction)

    const codeBodyTemplate = (rowData: Zeni.Transaction) => {
        return (<GenericBodyTemplate title={"Code"} value={rowData.id} />);
    };

    const descriptionBodyTemplate = (rowData: Zeni.Transaction) => {
        return (<GenericBodyTemplate title={"Code"} value={rowData.description} />);
    };

    const priceBodyTemplate = (rowData: Zeni.Transaction) => {
        return (<GenericBodyTemplate title={"Code"} value={tCrud.formatCurrency(rowData.price)} />);
    };

    const dateBodyTemplate = (rowData: Zeni.Transaction) => {
        return (<GenericBodyTemplate title={"Code"} value={rowData.data} />);
    };

    return (
        <div className="card">
            <h5>Recent Sales</h5>
            <DataTable value={tCrud.entities} rows={5} paginator responsiveLayout="scroll">
                {/*<Column header="Image" body={(data) => <img className="shadow-2" src={`/demo/images/product/${data.image}`} alt={data.image} width="50" />} />*/}
                {/*<Column field="descricao" header="Descricao" sortable style={{ width: '35%' }} />*/}
                {/*<Column field="price" header="Price" sortable style={{ width: '35%' }} body={(data) => formatCurrency(data.price)} />*/}
                {/*<Column header="View" style={{ width: '15%' }} body={() => (<><Button icon="pi pi-search" text /></>)} />*/}

                <Column field="code" header="Code" sortable body={codeBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
                <Column field="description" header="Description" sortable body={descriptionBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
                <Column field="price" header="Price" body={priceBodyTemplate} sortable></Column>
                <Column field="date" header="Date" sortable body={dateBodyTemplate} headerStyle={{ minWidth: '15rem' }}></Column>
            </DataTable>
        </div>
    )

}


