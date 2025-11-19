import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import { Menu } from 'primereact/menu';
import React from 'react';


export const CardTipoDeGasto = () =>{

    return (

        <div className="card h-full">
            <div className="flex justify-content-between align-items-center mb-5">
                <h5>Best Selling Products</h5>
                <div>

                </div>
            </div>


            <ul className="list-none p-0 m-0">
                <li className="flex flex-column md:flex-row md:align-items-center md:justify-content-between mb-4">
                    <div>
                        <span className="text-900 font-medium mr-2 mb-1 md:mb-0">Space T-Shirt</span>
                        <div className="mt-1 text-600">Clothing</div>
                    </div>
                    <div className="mt-2 md:mt-0 flex align-items-center">
                        <div className="surface-300 border-round overflow-hidden w-10rem lg:w-6rem" style={{ height: '8px' }}>
                            <div className="bg-orange-500 h-full" style={{ width: '50%' }} />
                        </div>
                        <span className="text-orange-500 ml-3 font-medium">%50</span>
                    </div>
                </li>
                <li className="flex flex-column md:flex-row md:align-items-center md:justify-content-between mb-4">
                    <div>
                        <span className="text-900 font-medium mr-2 mb-1 md:mb-0">Portal Sticker</span>
                        <div className="mt-1 text-600">Accessories</div>
                    </div>
                    <div className="mt-2 md:mt-0 ml-0 md:ml-8 flex align-items-center">
                        <div className="surface-300 border-round overflow-hidden w-10rem lg:w-6rem" style={{ height: '8px' }}>
                            <div className="bg-cyan-500 h-full" style={{ width: '16%' }} />
                        </div>
                        <span className="text-cyan-500 ml-3 font-medium">%16</span>
                    </div>
                </li>
                <li className="flex flex-column md:flex-row md:align-items-center md:justify-content-between mb-4">
                    <div>
                        <span className="text-900 font-medium mr-2 mb-1 md:mb-0">Supernova Sticker</span>
                        <div className="mt-1 text-600">Accessories</div>
                    </div>
                    <div className="mt-2 md:mt-0 ml-0 md:ml-8 flex align-items-center">
                        <div className="surface-300 border-round overflow-hidden w-10rem lg:w-6rem" style={{ height: '8px' }}>
                            <div className="bg-pink-500 h-full" style={{ width: '67%' }} />
                        </div>
                        <span className="text-pink-500 ml-3 font-medium">%67</span>
                    </div>
                </li>
                <li className="flex flex-column md:flex-row md:align-items-center md:justify-content-between mb-4">
                    <div>
                        <span className="text-900 font-medium mr-2 mb-1 md:mb-0">Wonders Notebook</span>
                        <div className="mt-1 text-600">Office</div>
                    </div>
                    <div className="mt-2 md:mt-0 ml-0 md:ml-8 flex align-items-center">
                        <div className="surface-300 border-round overflow-hidden w-10rem lg:w-6rem" style={{ height: '8px' }}>
                            <div className="bg-green-500 h-full" style={{ width: '35%' }} />
                        </div>
                        <span className="text-green-500 ml-3 font-medium">%35</span>
                    </div>
                </li>
                <li className="flex flex-column md:flex-row md:align-items-center md:justify-content-between mb-4">
                    <div>
                        <span className="text-900 font-medium mr-2 mb-1 md:mb-0">Mat Black Case</span>
                        <div className="mt-1 text-600">Accessories</div>
                    </div>
                    <div className="mt-2 md:mt-0 ml-0 md:ml-8 flex align-items-center">
                        <div className="surface-300 border-round overflow-hidden w-10rem lg:w-6rem" style={{ height: '8px' }}>
                            <div className="bg-purple-500 h-full" style={{ width: '75%' }} />
                        </div>
                        <span className="text-purple-500 ml-3 font-medium">%75</span>
                    </div>
                </li>
                <li className="flex flex-column md:flex-row md:align-items-center md:justify-content-between mb-4">
                    <div>
                        <span className="text-900 font-medium mr-2 mb-1 md:mb-0">Robots T-Shirt</span>
                        <div className="mt-1 text-600">Clothing</div>
                    </div>
                    <div className="mt-2 md:mt-0 ml-0 md:ml-8 flex align-items-center">
                        <div className="surface-300 border-round overflow-hidden w-10rem lg:w-6rem" style={{ height: '8px' }}>
                            <div className="bg-teal-500 h-full" style={{ width: '40%' }} />
                        </div>
                        <span className="text-teal-500 ml-3 font-medium">%40</span>
                    </div>
                </li>
            </ul>
        </div>

    )
}
