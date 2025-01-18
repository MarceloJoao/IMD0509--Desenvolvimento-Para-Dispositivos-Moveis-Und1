package model

//classe Gerente que herda de Pessoa e Funcionario
class Gerente(nome: String, cpf:String, dataNascimento:String, override var Matricula: String, override var salarioBase: Double = 3000.00): Pessoa(nome, cpf, dataNascimento), Funcionario{

    var salario: Double? = null
    //defini o salario base de 3000 para o gerente
    override fun calcularSalario(valor: Double) {
        salario = salarioBase + valor
    }

    override fun exibirSalario(): String {
        return "o salario do gerente é: $salario"
    }
}