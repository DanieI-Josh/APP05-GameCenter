# Mapa de requisitos — Game Center

| Requisito | Implementação |
| --- | --- |
| Exatamente seis Activities | Main, Central, Scanner, Arena, Cofre e Randomizador declaradas no Manifest |
| Java, XML e View Binding | Todas as telas e `item_opcao.xml` |
| Identificação dos integrantes | `strings.xml`, apresentação com Josh, Cecília, matrículas e curso |
| ConstraintLayout | Raiz de Main, Scanner, Arena, Cofre e Randomizador |
| Menu rolável | Raiz NestedScrollView, fillViewport e um LinearLayout vertical direto em `activity_central.xml` |
| Apelido e extras | `Navegacao.EXTRA_APELIDO`, validação na entrada e em cada destino |
| Scanner, cinco medidas | `AnaliseMensagem` e `ScannerActivity`; validação e botão Limpar |
| Arena melhor de três | `RegrasArena` e `ArenaActivity`; ganha ao alcançar dois pontos, empate não pontua |
| Sons locais | `res/raw/vitoria.wav`, `derrota.wav`, `sorteio.wav` |
| Ciclo de vida do MediaPlayer | Instâncias em onCreate, reutilizadas, pausadas em onPause e liberadas/nulas em onDestroy |
| Código aleatório | `PartidaCofre`, quatro dígitos diferentes, até oito tentativas válidas |
| Pistas e histórico | `CofreActivity`, verde/✓, amarelo/↔, cinza/×; histórico textual e visual |
| Conclusão do Cofre | Vitória/derrota, código, tentativas e diferença entre timestamps de System.currentTimeMillis |
| Cadastro por categoria | `Opcao`, ArrayList e Spinner com Filmes, Comidas, Lugares e Desafios |
| RecyclerView | `OpcaoAdapter`, `OpcaoViewHolder` e binding do cartão |
| Inserir e remover | notifyItemInserted + scrollToPosition; clique longo com objeto e notifyItemRemoved |
| Lista e grade | LinearLayoutManager e GridLayoutManager no mesmo Adapter |
| Sorteio filtrado | `Sorteador`, apenas itens da categoria selecionada; mensagem se vazia |
| Retorno normal | finish() preserva a Central existente |
| Encerrar sessão | CLEAR_TOP e SINGLE_TOP em `TelaActivity.configurarRetorno` |
| Último módulo | EXTRA_MODULO validado e MainActivity.onNewIntent() |
| Logs de ciclo de vida | TelaActivity registra onCreate, onResume, onStop e onDestroy com tag por classe concreta |
| Animação XML | res/anim/entrada.xml, 400 ms na Central |
| ViewPropertyAnimator | Botão de entrada 350 ms; resultados e escolhas 400 ms |
| Recursos e acessibilidade | strings, colors, dimens; imagens com contentDescription; campos com inputType |
| Preservação na rotação | Estado pequeno de Scanner, Arena, Cofre, Randomizador e último módulo em Bundle |

A classe abstrata `TelaActivity` compartilha comportamento; não é declarada no Manifest nem pode ser instanciada como uma tela adicional. As classes de regra não são Activities.

O projeto não usa findViewById, android:onClick, Handler, Thread, Runnable, Compose, banco de dados, permissões de internet ou RecyclerView dentro de ScrollView. Não usa post/postDelayed/withEndAction para agendar ações. O botão de entrada navega pelo callback AnimatorListenerAdapter ao fim da animação.

As regras foram baseadas no documento do APP05 e nas Aulas 01–07. O visual retrô foi aplicado em rosa, roxo e azul, com um controle original em pixel art, ícone adaptativo e recursos locais. A publicação do APK no Drive e a apresentação são etapas separadas.
