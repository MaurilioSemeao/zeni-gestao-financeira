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
                <Button label="New" icon="pi pi-plus" severity="success" className=" mr-2" onClick={openNew} />
                <Button label="Delete" icon="pi pi-trash" severity="danger" onClick={confirmDeleteSelected} disabled={!selectedGeneric || !(selectedGeneric as any).length} />
            </div>
        </React.Fragment>
    );
};
