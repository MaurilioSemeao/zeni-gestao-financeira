import React from 'react';


interface  InfoCadr{
    surname: string;
    finalNumber: string;
    expenses: number;
    quantityOfPurchases: number;

}



export const CardAccount = ( iCadr :InfoCadr) => {

    const {surname, finalNumber, expenses, quantityOfPurchases } = iCadr;

    const formatCurrency = (value: number) => {
        return value?.toLocaleString('en-US', {
            style: 'currency',
            currency: 'USD'
        });
    };

    return (
        <div className="w-20rem flex-shrink-0 ">
            <div className="card mb-0 h-full">
                <div className="flex justify-content-between mb-2">
                    <div>
                        <div className="text-900 font-medium text-xl">Gastos {formatCurrency(expenses)}</div>
                        <div className="mt-3 mb-2">
                             <span className="top-50 text-green-500 font-medium">{quantityOfPurchases} compras</span>
                        </div>
                    </div>
                    <div className="flex align-items-center justify-content-center bg-blue-100 border-round" style={{ width: '2.5rem', height: '2.5rem' }}>
                        <i className="pi pi-id-card text-blue-500 text-xl" />
                    </div>

                </div>
                <div className="flex justify-content-between">
                        <span className="block text-500 font-medium">{surname.toUpperCase()} </span>
                        <span className="block text-500 font-medium">- {finalNumber} </span>
                </div>

            </div>
        </div>
    );
};
