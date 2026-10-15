# Verificação — Game Center

- Compilação `assembleDebug`: concluída.
- Testes locais `testDebugUnitTest`: **15 testes aprovados**, sem falhas.
- Android Lint: **zero erros**; avisos abaixo.
- Verificador instrumentado externo: **70 verificações aprovadas** no emulador AOSP Android 11/API 30.
- Auditoria de estrutura: seis Activities, menu rolável com filho único, recursos e descrições de imagens, RecyclerView fora de rolagem externa, três WAVs válidos, notificações pontuais e ausência das APIs proibidas.

## Cobertura

Os testes locais verificam Scanner (inclusive acentos e espaços), todas as nove combinações de Pedra/Papel/Tesoura, término ao atingir duas vitórias, geração de mil códigos válidos incluindo zero inicial, classificação de pistas, limite de oito tentativas, vitória, derrota, tentativas inválidas, tempo, cópia do histórico, serialização e sorteio restrito à categoria.

No Android foram conferidos identificação do grupo; entrada inválida e válida; apelido recebido; rolagem da Central; Scanner e limpeza; retorno por finish; empates, vitórias, derrotas e reinício da Arena; criação/liberação dos MediaPlayers; Cofre com entrada inválida, vitória, derrota e oito linhas de pistas; cadastro, exclusão real por clique longo, lista/grade, sorteio filtrado, lista vazia e categoria vazia; rotação do Randomizador preservando registros e grade; rejeição de extras ausentes nas cinco telas de destino.

Os quatro encerramentos de sessão geraram quatro chamadas a onNewIntent na apresentação original. As Activities intermediárias foram destruídas. As capturas das seis telas e do Randomizador na orientação horizontal foram inspecionadas.

O verificador Android é externo e não integra o projeto nem o APK entregue. As entradas usadas nos testes não são dados fixos dos jogos. O Cofre gera um novo segredo em cada partida.

## Limites

Emulador isolado, sem uso do celular conectado. Os WAVs e o ciclo de vida dos players foram conferidos, mas o emulador foi iniciado sem saída de áudio no computador; confira a reprodução audível no dispositivo da apresentação. Não há vídeo gravado, upload no Drive ou repositório publicado. Esta é a versão funcional com visual genérico solicitada pelo grupo.

SDK 37.0, AGP 9.3.3, Gradle 9.5.0, fontes Java 17, mínimo Android 6.0/API 23. O APK de depuração é para instalação direta. Não houve teste em todas as versões ou modelos de Android.

## Avisos do Lint

- `GradleDependency` (Warning): A newer version of androidx.recyclerview:recyclerview than 1.2.1 is available: 1.3.0
- `DataExtractionRules` (Warning): The attribute `android:allowBackup` is deprecated from Android 12 and higher and may be removed in future versions. Consider adding the attribute `android:dataExtractionRules` specifying an `@xml` resource which configures cloud backups and device transfers on Android 12 and higher.
- `DisableBaselineAlignment` (Warning): Set `android:baselineAligned="false"` on this element for better performance
- `UnusedResources` (Warning): The resource `R.string.dica_lista` appears to be unused
- `Autofill` (Warning): Missing `autofillHints` attribute
- `Autofill` (Warning): Missing `autofillHints` attribute
- `Autofill` (Warning): Missing `autofillHints` attribute
- `Autofill` (Warning): Missing `autofillHints` attribute
