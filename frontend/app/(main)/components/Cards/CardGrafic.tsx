import { Chart } from 'primereact/chart';
import { Button } from 'primereact/button';
import { Menu } from 'primereact/menu';
import { ChartData, ChartOptions } from 'chart.js';
import React, { useContext, useEffect, useRef, useState } from 'react';
import { ProductService } from '@/demo/service/ProductService';
import { CardAccount } from '@/app/(main)/components/Cards/CardAccount';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { LayoutContext } from '@/layout/context/layoutcontext';
import { Demo } from '@/types';

const lineData: ChartData = {
    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
    datasets: [
        {
            label: 'First Dataset',
            data: [65, 59, 80, 81, 56, 55, 40],
            fill: false,
            backgroundColor: '#2f4860',
            borderColor: '#2f4860',
            tension: 0.4
        },
        {
            label: 'Second Dataset',
            data: [28, 48, 40, 19, 86, 27, 90],
            fill: false,
            backgroundColor: '#00bb7e',
            borderColor: '#00bb7e',
            tension: 0.4
        }
    ]
};


export const CardGrafic = () =>{
    const [products, setProducts] = useState<Demo.Product[]>([]);
    const menu1 = useRef<Menu>(null);
    const menu2 = useRef<Menu>(null);
    const [lineOptions, setLineOptions] = useState<ChartOptions>({});
    const { layoutConfig } = useContext(LayoutContext);

    const applyLightTheme = () => {
        const lineOptions: ChartOptions = {
            plugins: {
                legend: {
                    labels: {
                        color: '#495057'
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: '#495057'
                    },
                    grid: {
                        color: '#ebedef'
                    }
                },
                y: {
                    ticks: {
                        color: '#495057'
                    },
                    grid: {
                        color: '#ebedef'
                    }
                }
            }
        };

        setLineOptions(lineOptions);
    };

    const applyDarkTheme = () => {
        const lineOptions = {
            plugins: {
                legend: {
                    labels: {
                        color: '#ebedef'
                    }
                }
            },
            scales: {
                x: {
                    ticks: {
                        color: '#ebedef'
                    },
                    grid: {
                        color: 'rgba(160, 167, 181, .3)'
                    }
                },
                y: {
                    ticks: {
                        color: '#ebedef'
                    },
                    grid: {
                        color: 'rgba(160, 167, 181, .3)'
                    }
                }
            }
        };

        setLineOptions(lineOptions);
    };

    useEffect(() => {
        ProductService.getProductsSmall().then((data) => setProducts(data));
    }, []);

    useEffect(() => {
        if (layoutConfig.colorScheme === 'light') {
            applyLightTheme();
        } else {
            applyDarkTheme();
        }
    }, [layoutConfig.colorScheme]);


    return (
        <div>
            <div className="card">
                <h5>Sales Overview</h5>
                <Chart type="line" data={lineData} options={lineOptions} />
            </div>


            {/*<div className="card">*/}
            {/*    <div className="flex align-items-center justify-content-between mb-4">*/}
            {/*        <h5>Notifications</h5>*/}
            {/*        <div>*/}
            {/*            <Button type="button" icon="pi pi-ellipsis-v" rounded text className="p-button-plain" onClick={(event) => menu2.current?.toggle(event)} />*/}
            {/*            <Menu*/}
            {/*                ref={menu2}*/}
            {/*                popup*/}
            {/*                model={[*/}
            {/*                    { label: 'Add New', icon: 'pi pi-fw pi-plus' },*/}
            {/*                    { label: 'Remove', icon: 'pi pi-fw pi-minus' }*/}
            {/*                ]}*/}
            {/*            />*/}
            {/*        </div>*/}
            {/*    </div>*/}

            {/*    <span className="block text-600 font-medium mb-3">TODAY</span>*/}
            {/*    <ul className="p-0 mx-0 mt-0 mb-4 list-none">*/}
            {/*        <li className="flex align-items-center py-2 border-bottom-1 surface-border">*/}
            {/*            <div className="w-3rem h-3rem flex align-items-center justify-content-center bg-blue-100 border-circle mr-3 flex-shrink-0">*/}
            {/*                <i className="pi pi-dollar text-xl text-blue-500" />*/}
            {/*            </div>*/}
            {/*            <span className="text-900 line-height-3">*/}
            {/*                    Richard Jones*/}
            {/*                    <span className="text-700">*/}
            {/*                        {' '}*/}
            {/*                        has purchased a blue t-shirt for <span className="text-blue-500">79$</span>*/}
            {/*                    </span>*/}
            {/*                </span>*/}
            {/*        </li>*/}
            {/*        <li className="flex align-items-center py-2">*/}
            {/*            <div className="w-3rem h-3rem flex align-items-center justify-content-center bg-orange-100 border-circle mr-3 flex-shrink-0">*/}
            {/*                <i className="pi pi-download text-xl text-orange-500" />*/}
            {/*            </div>*/}
            {/*            <span className="text-700 line-height-3">*/}
            {/*                    Your request for withdrawal of <span className="text-blue-500 font-medium">2500$</span> has been initiated.*/}
            {/*                </span>*/}
            {/*        </li>*/}
            {/*    </ul>*/}

            {/*    <span className="block text-600 font-medium mb-3">YESTERDAY</span>*/}
            {/*    <ul className="p-0 m-0 list-none">*/}
            {/*        <li className="flex align-items-center py-2 border-bottom-1 surface-border">*/}
            {/*            <div className="w-3rem h-3rem flex align-items-center justify-content-center bg-blue-100 border-circle mr-3 flex-shrink-0">*/}
            {/*                <i className="pi pi-dollar text-xl text-blue-500" />*/}
            {/*            </div>*/}
            {/*            <span className="text-900 line-height-3">*/}
            {/*                    Keyser Wick*/}
            {/*                    <span className="text-700">*/}
            {/*                        {' '}*/}
            {/*                        has purchased a black jacket for <span className="text-blue-500">59$</span>*/}
            {/*                    </span>*/}
            {/*                </span>*/}
            {/*        </li>*/}
            {/*        <li className="flex align-items-center py-2 border-bottom-1 surface-border">*/}
            {/*            <div className="w-3rem h-3rem flex align-items-center justify-content-center bg-pink-100 border-circle mr-3 flex-shrink-0">*/}
            {/*                <i className="pi pi-question text-xl text-pink-500" />*/}
            {/*            </div>*/}
            {/*            <span className="text-900 line-height-3">*/}
            {/*                    Jane Davis*/}
            {/*                    <span className="text-700"> has posted a new questions about your product.</span>*/}
            {/*                </span>*/}
            {/*        </li>*/}
            {/*    </ul>*/}
            {/*</div>*/}
        </div>
    )
}
