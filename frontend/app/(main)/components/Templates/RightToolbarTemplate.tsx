import React from 'react';
import { FileUpload } from 'primereact/fileupload';
import { Button } from 'primereact/button';


interface Props {
    exportCSV: () => void;
}

export const RightToolbarTemplate = ({exportCSV}: Props) => {
    return (
        <React.Fragment>
            <FileUpload mode="basic" accept="image/*" maxFileSize={1000000} chooseLabel="Importar" className="mr-2 inline-block" />
            <Button label="Exportar" icon="pi pi-upload" severity="help" onClick={exportCSV} />
        </React.Fragment>
    );
};
