'use client'

import { cartaoService} from '@/service/CartaoService';
import {invoiceService} from '@/service/InvoiceService';
import {transactionService} from '@/service/TransactionService';

import { Zeni } from '@/types/zeni';
import React, { createContext, ReactNode, useCallback, useContext, useEffect, useState } from 'react';

interface DashBoardContextType{
    user: Zeni.User | null;
    cards: Zeni.Card[];
    invoices: Zeni.Invoice[];
    transactions: Zeni.Transaction[];
    loading: boolean;
    fetchData: () => Promise<void>;
}

const DashBoardContext = createContext<DashBoardContextType | undefined>(undefined);

export const DashboardProvider = ({ children }: {children: ReactNode}) => {
    const [user, setUser] = useState<Zeni.User | null>(() =>{
        if(typeof window !== "undefined"){
            const savedUser = localStorage.getItem('USER_DATA');
           return savedUser ? JSON.parse(savedUser) : null;
        }
        return null;
    });
    const [cards, setCards] = useState<Zeni.Card[]>([]);
    const [invoices, setInvoices] = useState<Zeni.Invoice[]>([]);
    const [transactions, setTransactions] = useState<Zeni.Transaction[] >([]);
    const [loading, setLoading] = useState(true);

    const fetchData = useCallback(async () =>{
            try{

                setLoading(true);


                const [cardData, transactionData,invoiceData ] = await Promise.all([
                    cartaoService.getAll(),
                    transactionService.getAll(),
                    invoiceService.getAll(),
                ]);

                setCards(cardData);
                setInvoices(invoiceData);
                setTransactions(transactionData);


            }catch(error){
                console.log(`Erro ao carregar dados do dashboard: ${error}`);
            }
            finally {
                setLoading(false);
            }
        },[])


    const value = {user, cards, invoices, transactions, loading, fetchData};

    return(
        <DashBoardContext.Provider value={value}>
            {children}
        </DashBoardContext.Provider>
    );

}

export const useDashboard = () => {
    const context = useContext(DashBoardContext);
    if(context === undefined){
        throw new Error('useDashboard deve ser usado dentro de um DashboardPrivider');
    }
    return context;
}
