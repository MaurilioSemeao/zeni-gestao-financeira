// utils/formHandlers.ts
import { RadioButtonChangeEvent } from 'primereact/radiobutton';
import { InputNumberValueChangeEvent } from 'primereact/inputnumber';
import React from 'react';

export function createFormHandlers<T>(
    setState: React.Dispatch<React.SetStateAction<T>>
) {
    const onInputChange = (
        e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>,
        name: keyof T
    ) => {
        const value = e.target?.value ?? '';
        setState((prev) => ({ ...prev, [name]: value }));
    };

    const onInputNumberChange = (
        e: InputNumberValueChangeEvent,
        name: keyof T
    ) => {
        const value = e.value ?? 0;
        setState((prev) => ({ ...prev, [name]: value }));
    };

    const onCategoryChange = (e: RadioButtonChangeEvent, name: keyof T) => {
        const value = e.target.value;
        setState((prev) => ({ ...prev, [name]: value }));
    };

    return { onInputChange, onInputNumberChange, onCategoryChange };
}
