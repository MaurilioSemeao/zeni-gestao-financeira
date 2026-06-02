import React from 'react';
import { Button } from 'primereact/button';

interface Props<T> {
    openNew: () => void;
    confirmDeleteSelected: () => void;
    selectedGeneric: T | T[];
}

export const LeftToolbarTemplate = <T,> (
    {
        openNew,
        confirmDeleteSelected,
        selectedGeneric
    }: Props<T>) => {


    return (
        <React.Fragment>

            <div className="my-2">
                <Button label="Novo" icon="pi pi-plus" severity="success" className=" mr-2" onClick={openNew} />
                <Button label="Excluir" icon="pi pi-trash" severity="danger" onClick={confirmDeleteSelected} disabled={!selectedGeneric || !(selectedGeneric as any).length} />
            </div>
        </React.Fragment>
    );
};
