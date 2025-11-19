import { ReactNode } from 'react';


interface Props {
    title: string,
    value: string | number ,
}


export const GenericBodyTemplate = ({ title, value }: Props) => {
    return (
        <>
            <span className="p-column-title">{title}</span>
            {String(value)}
        </>
    );
};
