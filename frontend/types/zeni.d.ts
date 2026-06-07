declare namespace Zeni {
    type Usuario = {
        id: number;
        nome: string;
        username: string;
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
        categoria: Category | null;
    };

    type Invoice = {
        id: number;
        saldoExtrato: number;
        status: string;
        mesReferencia: string;
    };

   type Card = {
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
       padrao?: boolean;
    }

    type ResumoCategoria = {
       id: number;
       nomeCategoria: string;
       valorTotal: number;
       porcentagem: number;
    }

    type ResumoCartao = {
        apelidoCartao: string;
        ultimosDigitos: string;
        valorTotal: number;
        quantidadeCompras: number;
        porcentagem: number;
    }
}
