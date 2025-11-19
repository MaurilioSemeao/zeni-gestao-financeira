/* eslint-disable @next/next/no-img-element */
'use client';
import { useRouter } from 'next/navigation';
import React, { useContext, useMemo, useRef, useState } from 'react';
import { Checkbox } from 'primereact/checkbox';
import { Button } from 'primereact/button';
import { Password } from 'primereact/password';
import { LayoutContext } from '../../../../layout/context/layoutcontext';
import { InputText } from 'primereact/inputtext';
import { classNames } from 'primereact/utils';
import { Toast } from 'primereact/toast';
import { LoginService} from '@/service/LoginService';

const NewUserPage = () => {

    let emptyUser: Zeni.User ={
        id: 0,
        username: '',
        email: '',
        password: '',
    };

    const loginService = useMemo(() => new LoginService(), [])

    const [user, setUser] = useState<Zeni.User>(emptyUser);

    const { layoutConfig } = useContext(LayoutContext);
    const router = useRouter();

    const containerClassName = classNames('surface-ground flex align-items-center justify-content-center min-h-screen min-w-screen overflow-hidden', { 'p-input-filled': layoutConfig.inputStyle === 'filled' });
    const toast = useRef<Toast>(null)


    const onInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>, name: string) => {
        const val = (e.target && e.target.value) || '';
        setUser(prev => ({ ...prev, [name]: val }) );
    }

    const creatNewUser =() =>{

        loginService.creatNewUser(user).then((response) =>{
            toast.current?.show({
                severity: 'success',
                summary: 'New user created successfully',
                detail: 'User created successfully',
            })
            setTimeout(()=>{
                router.push('/auth/login');
                // window.location.reload();
            },2000)

        }).catch((error) =>{
            toast.current?.show({
                severity: 'error',
                summary: 'Failed to create new user',
                detail: error.message,
            })

        })

    }


    return (
        <div className={containerClassName}>
            <Toast  ref={toast}/>
            <div className="flex flex-column align-items-center justify-content-center">
                <img src={`/layout/images/logo-${layoutConfig.colorScheme === 'light' ? 'dark' : 'white'}.svg`} alt="Sakai logo" className="mb-5 w-6rem flex-shrink-0" />
                <div
                    style={{
                        borderRadius: '56px',
                        padding: '0.3rem',
                        background: 'linear-gradient(180deg, var(--primary-color) 10%, rgba(33, 150, 243, 0) 30%)'
                    }}
                >
                    <div className="w-full surface-card py-8 px-5 sm:px-8" style={{ borderRadius: '53px' }}>
                        <div className="text-center mb-5">
                            <div className="text-900 text-3xl font-medium mb-3">
                                Faça seu login </div>
                        </div>

                        <div>


                            <label htmlFor="nome1" className="block text-900 text-xl font-medium mb-2">
                                Nome
                            </label>
                            <InputText
                                id="nome1"
                                value={user.username}
                                type="text"
                                placeholder="Digite Seu nome"
                                className="w-full md:w-30rem mb-5" style={{ padding: '1rem' }}
                                onChange={(e) => onInputChange(e, 'username')}
                            />

                            <label htmlFor="email1" className="block text-900 text-xl font-medium mb-2">
                                Email
                            </label>
                            <InputText
                                id="email1"
                                value={user.email}
                                type="text"
                                placeholder="Digite Seu email"
                                className="w-full md:w-30rem mb-5" style={{ padding: '1rem' }}
                                onChange={(e) => onInputChange(e, 'email')}
                            />

                            <label htmlFor="password1" className="block text-900 font-medium text-xl mb-2">
                                Password
                            </label>
                            <Password
                                inputId="password1"
                                value={user.password}
                                placeholder="Password"
                                toggleMask className="w-full mb-5"
                                inputClassName="w-full p-3 md:w-30rem"
                                onChange={(e) => setUser(prev => ({ ...prev, password: e.target.value }))}
                            >
                            </Password>

                            <div className="flex align-items-center justify-content-between mb-5 gap-5">

                                <a
                                    className="font-medium no-underline ml-2 text-right cursor-pointer"
                                    style={{ color: 'var(--primary-color)' }}
                                    onClick={() => router.push('/auth/login') }
                                >
                                    Já Possuo uma conta?
                                </a>

                            </div>
                            <Button label="Entras" className="w-full p-3 text-xl" onClick={() => creatNewUser()}></Button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default NewUserPage;
