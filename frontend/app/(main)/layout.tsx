import { Metadata } from 'next';
import Layout from '../../layout/layout';
import { DataTable } from 'primereact/datatable';
import { DashboardProvider } from '@/context/DashBoardContext';

interface AppLayoutProps {
    children: React.ReactNode;
}

export const metadata: Metadata = {
    title: 'Zeni - Gestão Financeira',
    description: 'Sistema de controle financeiro pessoal.',
    robots: { index: false, follow: false },
    viewport: { initialScale: 1, width: 'device-width' },
    openGraph: {
        type: 'website',
        title: 'Zeni - Gestão Financeira',
        description: 'Sistema de controle financeiro pessoal.',
        ttl: 604800
    },
    icons: {
        icon: ''
    }
};

export default function AppLayout({ children }: AppLayoutProps) {
    return (
        <Layout>
            <DashboardProvider>
                {children}
            </DashboardProvider>
        </Layout>
    )
}
