package utils

import DAO.BancoDAO
import model.Funcionario
import model.Gerente
import model.Pessoa
import model.Vendedor

class RhFunctions(){
    var banco = BancoDAO.getInstance()


    fun cadastrarFuncionario(nome: String, cpf: String, dataNascimento: String, matricula: String, setor: String? = null) {
        val funcionario: Funcionario = if (setor != null) {
            // Se o setor for fornecido, cria um Vendedor
            Vendedor(nome, cpf, dataNascimento, matricula, setor)
        } else {
            // Caso contrário, cria um Gerente
            Gerente(nome, cpf, dataNascimento, matricula)
        }
        //adcionando ao ArrayList
        banco.getArrayPessoas().add(funcionario as Pessoa)
        println("Cadastro de ${funcionario::class.simpleName} realizado com sucesso.")
    }
    fun pesquisarFuncionario(cpf:String){
        val listar = banco.getArrayPessoas()

        val funcionario = listar.find { it.cpf == cpf }

        if(funcionario != null){
            when(funcionario){
                is Gerente -> (println("Gerente localizado"))
                is Vendedor -> (println("Vendedor localizado"))
            }
        }else{
            println("Nenhum funcionario encontrado para o cpf $cpf")
        }
    }

    fun excluirFuncionario(cpf:String){
       val listar = banco.getArrayPessoas()

       val funcionario = listar.find { it.cpf == cpf }

       if(funcionario != null){
           listar.remove(funcionario)
           println("Funcionario removido com sucesso")
       }else{
           println("Funcionario não encontrado")
       }
    }

    private fun buscarPessoa(cpf:String): Funcionario?{
        val listar = banco.getArrayPessoas()
        //se for igual ao parametro
        return listar.find { it.cpf == cpf } as? Funcionario
    }

    fun imprimirInformacoesFuncionario(cpf: String) {
        val funcionario = buscarPessoa(cpf)

        if (funcionario != null) {
            when (funcionario) {
                //se for Gerente
                is Gerente -> {
                    println()
                    println("*************************************************")
                    println("Nome: ${funcionario.nome}")
                    println("CPF: ${funcionario.cpf}")
                    println("Nascimento: ${funcionario.dataNascimento}")
                    println("Matricula: ${funcionario.Matricula}")
                    println("Salario do gerente: ${funcionario.salario}")
                }
                //se for Vendedor
                is Vendedor -> {
                    println("**************************************************")
                    println("Nome: ${funcionario.nome}")
                    println("CPF: ${funcionario.cpf}")
                    println("Nascimento: ${funcionario.dataNascimento}")
                    println("Matricula: ${funcionario.Matricula}")
                    println("Setor: ${funcionario.setor}")
                    println("Salario do vendedor: ${funcionario.salario}")
                }
                else -> {
                    // Caso haja outros tipos de funcionários no futuro
                    println("Tipo de funcionário não identificado.")
                }
            }
        } else {

            println("Nenhum funcionário encontrado para o CPF $cpf")
        }
    }


    fun listarFuncionarios(){
        val listar = banco.getArrayPessoas()
        //se a lista for vazia
        if(listar.isEmpty()){
            println("Nenhum funcionário cadastrado")
        }else{
            //listando
            println()
            println("############ - Gerentes - ############")
            for(funcionario in listar){
                if(funcionario is Gerente){
                    println("Nome: ${funcionario.nome}")
                    println("Matricula: ${funcionario.Matricula}")
                }
            }
            println()

            println("############ - Vendedores - ############")
            for(funcionario in listar){
                if(funcionario is Vendedor){
                    println("Nome: ${funcionario.nome}")
                    println("Matricula: ${funcionario.Matricula}")
                }
            }
        }
    }

    fun calcularSalario(cpf: String, valor: Double) {
        val funcionario = buscarPessoa(cpf)

        if (funcionario != null) {
            funcionario.calcularSalario(valor) //chama o calcular salario de acordo com cada funcionario

        } else {
            println("Funcionário com CPF '$cpf' não foi encontrado.")
        }
    }

}
