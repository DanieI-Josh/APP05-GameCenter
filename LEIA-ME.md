# Game Center — versão funcional

Integrantes:
- Josh — 20251012000210
- Cecília — 20251012000856
- Curso: Análise e Desenvolvimento de Sistemas

Projeto acadêmico em Java e XML. Esta entrega tem aparência genérica a pedido do grupo. As seis telas, validações, desafios, sons locais, animações e navegação estão implementados. A personalização final será feita pelo grupo.

## Abrir no Android Studio

1. Extraia o ZIP em `C:\Users\USER\Documents\ProjetosAndroid\APP05-GameCenter`.
2. No Android Studio, use **File > Open** e selecione a pasta **GameCenter** que contém `settings.gradle`, `build.gradle` e `app`.
3. Aguarde a sincronização. Use o JDK incorporado ao Android Studio nas configurações do Gradle.
4. Se o Studio solicitar os componentes, instale o SDK Android API 37.0. O SDK não vai dentro do ZIP.
5. Selecione um emulador Android 6.0/API 23 ou superior e execute a configuração `app` no botão Run.

Use uma versão recente do Android Studio compatível com AGP 9.3.3. O projeto inclui o wrapper Gradle 9.5.0 e fontes Java 17. As dependências precisam ser baixadas na primeira sincronização, caso ainda não estejam no computador. O aplicativo instalado funciona sem internet.

## Telas e regras

- **MainActivity:** identificação do grupo, apelido com pelo menos três caracteres, botão animado e último módulo recebido em `onNewIntent()`.
- **CentralActivity:** menu com quatro painéis, saudação com apelido, rolagem e animação XML.
- **ScannerActivity:** caracteres sem espaços, palavras, vogais, texto invertido e palíndromo; validação e limpeza. Vogais acentuadas também são contadas. Palíndromos ignoram espaços e caixa, mantendo pontuação e acentos.
- **ArenaActivity:** Pedra/Papel/Tesoura até duas vitórias, sem pontos por empate; escolhas e resultado animados, sons e nova partida.
- **CofreActivity:** quatro dígitos diferentes, no máximo oito tentativas válidas, pistas verdes/amarelas/cinzas, histórico, resultado e tempo. O código pode começar com zero.
- **RandomizadorActivity:** quatro categorias (Filmes, Comidas, Lugares, Desafios), cadastro, exclusão por clique longo, lista/grade e sorteio somente da categoria selecionada. Há um exemplo inicial em cada categoria. Os itens são locais à tela; não há armazenamento permanente.

Retornar de um desafio usa `finish()`; encerrar sessão usa `CLEAR_TOP | SINGLE_TOP` e envia o módulo para a apresentação existente. O código registra o ciclo de vida nas tags `CicloMainActivity`, `CicloCentralActivity` e equivalentes para cada desafio. Filtre o Logcat por `Ciclo`.

Estado pequeno de interface e jogos é preservado em recriações de tela. Dados não são gravados em banco ou arquivo. O tempo do cofre considera todo o intervalo entre início e conclusão, inclusive tempo fora do primeiro plano.

## Onde aplicar a identidade visual depois

- `app/src/main/res/values/colors.xml`: paleta e cores das pistas.
- `app/src/main/res/values/styles.xml`: estilos dos textos, campos e botões.
- `app/src/main/res/values-v27/styles.xml`: configuração de tema para versões mais recentes; mantenha coerente com o tema padrão.
- `app/src/main/res/values/dimens.xml`: margens e tamanhos de texto.
- `app/src/main/res/values/strings.xml`: identificação, nome, orientações, categorias, mensagens e créditos.
- `app/src/main/res/layout/activity_*.xml`: organização das seis telas.
- `app/src/main/res/layout/item_opcao.xml`: cartão reutilizável do Randomizador.
- `app/src/main/res/drawable`: ícones vetoriais genéricos e fundo de painel.
- `app/src/main/res/raw`: três efeitos sonoros locais.
- `app/src/main/res/anim/entrada.xml`: animação reutilizável.

Preserve IDs, tipos dos componentes e referências de View Binding ao trocar o visual. A RecyclerView deve continuar fora da NestedScrollView. Preserve o único filho direto da rolagem da Central. As cores e símbolos das pistas do Cofre devem continuar distinguíveis e acompanhados da legenda.

## Estrutura Java

`TelaActivity` é uma classe abstrata compartilhada, não uma sétima tela. Centraliza insets, validação do apelido, navegação e logs. O Manifest declara exatamente seis Activities.

`AnaliseMensagem`, `RegrasArena`, `PartidaCofre` e `Sorteador` concentram regras testáveis. `Opcao` é o modelo e `OpcaoAdapter.OpcaoViewHolder` usa o binding do item e encaminha o objeto no clique longo. `Efeitos` reúne animação e reprodução de instâncias já criadas.

## Verificação manual sugerida

1. Tente entrar com apelido vazio e com dois caracteres. Entre com Josh ou Cecília e role a Central.
2. No Scanner, analise `Ana`, `A sacada da casa` e um texto comum; use Limpar e Voltar à central.
3. Na Arena, observe as escolhas, empates, pontuação, sons e fim da melhor de três. Use Nova partida.
4. No Cofre, tente um campo vazio e `1123`; confira que nenhuma chance é consumida. Use tentativas válidas e confira as pistas. Termine por acerto ou oito tentativas e reinicie.
5. No Randomizador, adicione itens de categorias diferentes, alterne lista/grade, sorteie e exclua por clique longo. Remova todos e tente sortear.
6. Rotacione durante um jogo. Volte pelo botão da tela e pelo botão do Android.
7. Encerre sessão de cada módulo e confira o último módulo na apresentação. No Logcat, observe `onNewIntent()` e a destruição da Central e do desafio.
8. Confira os sons no dispositivo escolhido para apresentação. O teste automatizado foi feito em emulador sem saída de áudio no computador.

## Entrega acadêmica posterior

Esta versão ainda receberá a identidade visual do grupo. O enunciado do APP05 solicita projeto completo, código no GitHub, APK por link do Google Drive no Teams, créditos e apresentação ou demonstração por vídeo. Nenhum repositório, upload ou vídeo é criado por este projeto. O APK incluído é de depuração, para instalação direta, não publicação na Play Store.
