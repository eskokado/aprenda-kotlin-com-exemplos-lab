// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class ConteudoEducacional(
    val nome: String,
    val duracao: Int = 60,
    val pontos: Int = 0
)

class Usuario(val nome: String) {
    private val concluidos = mutableListOf<ConteudoEducacional>()

    fun concluir(conteudo: ConteudoEducacional) {
        concluidos.add(conteudo)
    }

    fun pontuacaoTotal(): Int = concluidos.sumOf { it.pontos }

    fun conteudosConcluidos(): List<ConteudoEducacional> = concluidos.toList()
}

data class Formacao(
    val nome: String,
    val nivel: Nivel,
    val conteudos: List<ConteudoEducacional>
) {
    private val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        if (inscritos.contains(usuario)) {
            println("O usuário ${usuario.nome} já está matriculado em $nome.")
        } else {
            inscritos.add(usuario)
            println("O usuário ${usuario.nome} foi matriculado em $nome.")
        }
    }

    fun concluir(usuario: Usuario, conteudo: ConteudoEducacional) {
        require(inscritos.contains(usuario)) { "Usuário não matriculado em $nome" }
        require(conteudos.contains(conteudo)) { "Conteúdo não pertence à formação $nome" }
        usuario.concluir(conteudo)
    }

    fun pontosDoUsuario(usuario: Usuario): Int {
        val setConteudos = conteudos.toSet()
        return usuario.conteudosConcluidos().filter { setConteudos.contains(it) }.sumOf { it.pontos }
    }

    fun ranking(): List<Pair<Usuario, Int>> = inscritos
        .map { it to pontosDoUsuario(it) }
        .sortedWith(compareByDescending<Pair<Usuario, Int>> { it.second }.thenBy { it.first.nome })

    fun top(n: Int): List<Pair<Usuario, Int>> = ranking().take(n)
}

fun main() {
    val logica = ConteudoEducacional(nome = "Lógica de Programação", duracao = 90, pontos = 100)
    val poo = ConteudoEducacional(nome = "Programação Orientada a Objetos", duracao = 120, pontos = 120)
    val colecoes = ConteudoEducacional(nome = "Coleções em Kotlin", duracao = 80, pontos = 80)
    val coroutines = ConteudoEducacional(nome = "Kotlin Coroutines", duracao = 100, pontos = 150)

    val formacaoKotlinBasica = Formacao(
        nome = "Formação Kotlin Básica",
        nivel = Nivel.BASICO,
        conteudos = listOf(logica, poo, colecoes)
    )

    val formacaoKotlinAvancada = Formacao(
        nome = "Formação Kotlin Avançada",
        nivel = Nivel.DIFICIL,
        conteudos = listOf(coroutines, poo)
    )

    val ana = Usuario("Ana")
    val bruno = Usuario("Bruno")
    val carla = Usuario("Carla")

    formacaoKotlinBasica.matricular(ana)
    formacaoKotlinBasica.matricular(bruno)
    formacaoKotlinBasica.matricular(carla)

    formacaoKotlinAvancada.matricular(ana)
    formacaoKotlinAvancada.matricular(bruno)

    formacaoKotlinBasica.concluir(ana, logica)
    formacaoKotlinBasica.concluir(ana, poo)
    formacaoKotlinBasica.concluir(bruno, logica)
    formacaoKotlinBasica.concluir(carla, colecoes)

    formacaoKotlinAvancada.concluir(ana, coroutines)
    formacaoKotlinAvancada.concluir(bruno, poo)

    println("Ranking - ${formacaoKotlinBasica.nome}")
    formacaoKotlinBasica.ranking().forEachIndexed { i, (usuario, pontos) ->
        println("${i + 1}. ${usuario.nome} - ${pontos} pontos")
    }

    println()
    println("Ranking - ${formacaoKotlinAvancada.nome}")
    formacaoKotlinAvancada.ranking().forEachIndexed { i, (usuario, pontos) ->
        println("${i + 1}. ${usuario.nome} - ${pontos} pontos")
    }

    println()
    println("Top 2 - ${formacaoKotlinBasica.nome}")
    formacaoKotlinBasica.top(2).forEachIndexed { i, (usuario, pontos) ->
        println("${i + 1}. ${usuario.nome} - ${pontos} pontos")
    }
}
