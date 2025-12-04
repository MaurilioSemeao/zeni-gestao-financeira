
export declare namespace Zeni {
    type Usuario = {
        id: number;
        nome: string;
        email: string;
        senha: string;
    };

    type Transaction = {
        id: number;
        descricao: string;
        valor: number;
        tipo: string;
        meioPagamento: string;
        dataTransacao: string;
        categoriaId: number;
        cartaoId: number | null;
        contaId: number | null;

    };

    type Invoice = {
        id: number;
        valorTotal: number;
        status: string;
        mesReferencia: string;
    };

   type  Card ={
        id: number;
        apelido: string;
        ultimosDigitos: string;
        gastos: number;
        quantidadeCompras: number;
        limitValue: number;
    }

    type Category = {
       id: number;
       nome: string;
    }




}
