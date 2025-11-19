/* eslint-disable @next/next/no-img-element */

import Link from 'next/link';
import { classNames } from 'primereact/utils';
import React, { forwardRef, useContext, useImperativeHandle, useRef } from 'react';
import { AppTopbarRef } from '@/types';
import { LayoutContext } from './context/layoutcontext';
import { useDashboard } from '@/context/DashBoardContext';

const AppTopbar = forwardRef<AppTopbarRef>((props, ref) => {

    const { user } = useDashboard()

    const { layoutConfig, layoutState, onMenuToggle, showProfileSidebar } = useContext(LayoutContext);
    const menubuttonRef = useRef(null);
    const topbarmenuRef = useRef(null);
    const topbarmenubuttonRef = useRef(null);

    useImperativeHandle(ref, () => ({
        menubutton: menubuttonRef.current,
        topbarmenu: topbarmenuRef.current,
        topbarmenubutton: topbarmenubuttonRef.current
    }));


    const logout = () => {
        localStorage.removeItem('TOKEN_APPLICATION');
        localStorage.removeItem('USER_DATA');
        window.location.reload();
    }


    return (
        <div className="layout-topbar">
            <Link href="/" className="layout-topbar-logo">
                <img src={`/layout/images/logo-${layoutConfig.colorScheme !== 'light' ? 'white' : 'dark'}.svg`} width="47.22px" height={'35px'} alt="logo" />
                <span>ZENI</span>
            </Link>

            <button ref={menubuttonRef} type="button" className="p-link layout-menu-button layout-topbar-button" onClick={onMenuToggle}>
                <i className="pi pi-bars" />
            </button>

            <button ref={topbarmenubuttonRef} type="button" className="p-link layout-topbar-menu-button layout-topbar-button" onClick={showProfileSidebar}>
                <i className="pi pi-ellipsis-v" />
            </button>

            <div ref={topbarmenuRef} className={classNames('layout-topbar-menu', { 'layout-topbar-menu-mobile-active': layoutState.profileSidebarVisible })}>
                {/*<button type="button" className="p-link layout-topbar-button">*/}
                {/*    <i className="pi pi-calendar"></i>*/}
                {/*    <span>Calendar</span>*/}
                {/*</button>*/}
                <p className="p-link layout-topbar-button">Olá {user?.username}</p>

                <button type="button" className="p-link layout-topbar-button">
                    <i className="pi pi-user"></i>
                    <span>{user?.username}</span>
                </button>

                <a href="/" onClick={() => logout()}>
                    <button type="button" className="p-link layout-topbar-button">
                        <i className="pi pi-sign-out"></i>
                        <span>Sair</span>
                    </button>
                </a>
            </div>
        </div>
    );
});

AppTopbar.displayName = 'AppTopbar';

export default AppTopbar;
