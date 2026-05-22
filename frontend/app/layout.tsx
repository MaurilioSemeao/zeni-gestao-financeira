'use client';
import { LayoutProvider } from '../layout/context/layoutcontext';
import { PrimeReactProvider } from 'primereact/api';
import 'primereact/resources/primereact.css';
import 'primeflex/primeflex.css';
import 'primeicons/primeicons.css';
import '../styles/layout/layout.scss';
import '../styles/demo/Demos.scss';
import { useEffect, useState } from 'react';
import LoginPage from './(full-page)/auth/login/page';
import { usePathname } from 'next/navigation';
import { DashboardProvider } from '@/context/DashBoardContext';

interface RootLayoutProps {
    children: React.ReactNode;
}

const checkAuth = () =>{
    if(typeof window !== "undefined"){
        if(localStorage.getItem('TOKEN_APPLICATION') != undefined){
            return true;
        }
    }
    return false;
}



export default function RootLayout({ children }: RootLayoutProps) {

    const[pageLoaded, setPageLoaded] = useState(false);
    const[isAuth, setIsAuth] = useState(checkAuth());
    const pathName = usePathname();

    useEffect(() => {
        if(pathName.startsWith('/pages') || pathName == '/'){
            setIsAuth(checkAuth());
            setPageLoaded(true);

        }else{
            setIsAuth(true);
            setPageLoaded(true);
        }
    }, [pathName]);


    return (
        <html lang="en" suppressHydrationWarning>
            <head>
                <link id="theme-css" href={`/themes/lara-light-indigo/theme.css`} rel="stylesheet"></link>
            </head>
            <body>
                {isAuth ?
                <PrimeReactProvider>
                    <LayoutProvider>
                        <DashboardProvider>
                            {children}
                        </DashboardProvider>
                    </LayoutProvider>
                </PrimeReactProvider>

                :
                pageLoaded ?
                 <PrimeReactProvider>
                    <LayoutProvider>
                        <LoginPage />
                    </LayoutProvider>
                </PrimeReactProvider>
                :
                null
                }
            </body>
        </html>
    );
}
