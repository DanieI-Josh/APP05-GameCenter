# Game Center

Aplicativo Android acadêmico do APP05 — Pocket Arcade, desenvolvido em **Java e XML com View Binding**.

## Integrantes

| Nome | Matrícula |
| --- | --- |
| Josh | 20251012000210 |
| Cecília | 20251012000856 |

Curso: Análise e Desenvolvimento de Sistemas.

## Funcionalidades

O projeto possui exatamente seis telas:

1. **Apresentação:** identificação, validação do apelido e entrada animada.
2. **Central de Desafios:** menu rolável com os quatro módulos.
3. **Scanner de Mensagens:** caracteres sem espaços, palavras, vogais, texto invertido e palíndromo.
4. **Pedra, Papel e Tesoura:** partida até duas vitórias, placar, sons e animações.
5. **Código Secreto:** quatro dígitos diferentes, oito tentativas, pistas por posição e tempo ao concluir.
6. **Randomizador:** cadastro por categoria, sorteio filtrado, lista/grade e exclusão por clique longo.

A navegação usa Intents explícitas, extras validados, finish(), CLEAR_TOP, SINGLE_TOP e onNewIntent(). O ciclo de vida é registrado no Logcat. Não há banco de dados ou acesso à internet no aplicativo.

**Versão 1.1 com identidade retrô em rosa, roxo e azul, controle em pixel art, ícone adaptativo e layout horizontal do Randomizador.**

## Abrir no Android Studio

1. Clone o repositório ou use **Code > Download ZIP** e extraia os arquivos.
2. No Android Studio, use **File > Open** e selecione a raiz do projeto, que contém `settings.gradle` e a pasta `app`.
3. Aguarde a sincronização do Gradle e instale os componentes de SDK solicitados.
4. Use o JDK incorporado ao Android Studio e execute o módulo `app` em um emulador ou dispositivo.

Configuração: Android Gradle Plugin 9.3.3, Gradle 9.5.0, SDK 37.0, fontes Java 17 e Android mínimo 6.0/API 23. Use Android Studio compatível com essa versão do plugin. A primeira sincronização pode exigir internet para baixar dependências.

## Verificação

- Compilação do APK concluída.
- 15 testes locais das regras aprovados.
- 70 verificações instrumentadas externas aprovadas no emulador Android 11/API 30.
- Android Lint: zero erros; avisos e limites descritos em [VERIFICACAO.md](VERIFICACAO.md).

O verificador instrumentado externo não integra o código do aplicativo. Os testes locais estão em `app/src/test`.

## Identidade visual

A paleta e os recursos visuais estão descritos em [IDENTIDADE-VISUAL.md](IDENTIDADE-VISUAL.md). As regras dos quatro desafios foram preservadas.

## Documentação

- [Guia do projeto e personalização](LEIA-ME.md)
- [Mapa dos requisitos](MAPA-DOS-REQUISITOS.md)
- [Verificação e limitações](VERIFICACAO.md)

Os XMLs ficam em `app/src/main/res/layout`; textos, cores, dimensões e estilos ficam em `app/src/main/res/values`. Os sons são locais em `res/raw`.
