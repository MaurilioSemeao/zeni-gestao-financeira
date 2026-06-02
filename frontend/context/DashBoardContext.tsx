'use client'

import {cartaoService} from '@/service/CartaoService';
import {invoiceService} from '@/service/InvoiceService';
import {transactionService} from '@/service/TransactionService';
import {dashBoradService} from '@/service/DashBoardService'

import React, { createContext, ReactNode, useCallback, useContext, useEffect, useState } from 'react';

interface DashBoardContextType{
    user: Zeni.Usuario | null;
    cards: Zeni.Card[];
    invoices: Zeni.Invoice[];
    transactions: Zeni.Transaction[];
    resumoCategoria: Zeni.ResumoCategoria[];
    resumoCartao: Zeni.ResumoCartao[];
    loading: boolean;
    fetchData: () => Promise<void>;
    periodoCategoria: string;
    changePeriodoCategoria: (novoPeriodo: string) => Promise<void>;
}

const DashBoardContext = createContext<DashBoardContextType | undefined>(undefined);

export const DashboardProvider = ({ children }: {children: ReactNode}) => {
    const [user, setUser] = useState<Zeni.Usuario | null>(() =>{
        if(typeof window !== "undefined"){
            const savedUser = localStorage.getItem('USER_DATA');
           return savedUser ? JSON.parse(savedUser) : null;
        }
        return null;
    });
    const [cards, setCards] = useState<Zeni.Card[]>([]);
    const [invoices, setInvoices] = useState<Zeni.Invoice[]>([]);
    const [transactions, setTransactions] = useState<Zeni.Transaction[] >([]);
    const [resumoCategoria, setResumoCategoria] = useState<Zeni.ResumoCategoria[]>([]);
    const [resumoCartao, setResumoCartao] = useState<Zeni.ResumoCartao[]>([]);
    const [periodoCategoria, setPeriodoCategoria] = useState<string>("MENSAL");
    const [loading, setLoading] = useState(true);

    const fetchData = useCallback(async () =>{
            try{

                setLoading(true);


                const [cardData, transactionData, resumoCat, resumoCart] = await Promise.all([
                    cartaoService.getAll(),
                    transactionService.getAll(),
                    dashBoradService.getResumoCategoria(periodoCategoria),
                    dashBoradService.getResumoCartao(periodoCategoria)
                   // invoiceService.getAll(),
                ]);

                setCards(cardData);
                //setInvoices(invoiceData);
                setTransactions(transactionData);
                setResumoCategoria(resumoCat);
                setResumoCartao(resumoCart);


            }catch(error){
                console.log(`Erro ao carregar dados do dashboard: ${error}`);
            }
            finally {
                setLoading(false);
            }
        },[periodoCategoria]); // Add dependency to avoid stale closure of periodoCategoria if needed, but it uses the state value.

    const changePeriodoCategoria = useCallback(async (novoPeriodo: string) => {
        setPeriodoCategoria(novoPeriodo);
        try {
            const [catData, cartData] = await Promise.all([
                dashBoradService.getResumoCategoria(novoPeriodo),
                dashBoradService.getResumoCartao(novoPeriodo)
            ]);
            setResumoCategoria(catData);
            setResumoCartao(cartData);
        } catch(error) {
            console.log("Erro ao atualizar periodo", error);
        }
    }, []);

    const value = {user, cards, invoices, transactions, resumoCategoria, resumoCartao, loading, fetchData, periodoCategoria, changePeriodoCategoria};

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

