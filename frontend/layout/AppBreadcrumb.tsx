'use client';

import React from 'react';
import { usePathname } from 'next/navigation';
import { BreadCrumb } from 'primereact/breadcrumb';

export const AppBreadcrumb = () => {
    const pathname = usePathname();

    // Map routes to Portuguese names
    const routeMap: { [key: string]: string } = {
        '/': 'Dashboard',
        '/pages/cards': 'Cartões',
        '/pages/invoice': 'Extratos',
        '/pages/category': 'Categorias',
        '/pages/transaction': 'Transações',
    };

    // Parse dynamic subpaths (e.g. dynamic month routes like /pages/transaction/Junho%202026)
    const getBreadcrumbItems = () => {
        if (!pathname) return [];
        
        // Remove trailing slashes and split
        const parts = pathname.split('/').filter(p => p !== '');
        
        // Build cumulative paths and get labels
        const items = [];
        let cumulativePath = '';

        for (let i = 0; i < parts.length; i++) {
            const part = parts[i];
            cumulativePath += '/' + part;

            // Skip "pages" directory segment as it is just grouping in route
            if (part === 'pages') continue;

            // Check translation in route map, or decode URI for dynamic pages
            let label = routeMap[cumulativePath];
            if (!label) {
                // If it is dynamic transaction month, handle it
                if (cumulativePath.startsWith('/pages/transaction/')) {
                    label = `Transações de ${decodeURIComponent(part)}`;
                } else {
                    label = decodeURIComponent(part);
                }
            }

            items.push({
                label: label,
                url: cumulativePath
            });
        }

        return items;
    };

    const items = getBreadcrumbItems();
    const home = { icon: 'pi pi-home', url: '/' };

    // Don't render breadcrumb on home page as it is redundant
    if (pathname === '/') {
        return null;
    }

    return (
        <div className="mb-4">
            <BreadCrumb model={items} home={home} className="border-none bg-transparent p-0" />
        </div>
    );
};
