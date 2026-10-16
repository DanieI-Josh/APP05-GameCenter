# Identidade visual — Game Center

Versão 1.1. Tema retrô com painéis escuros, ícones em pixel art e detalhes em rosa, roxo e azul.

## Paleta

| Uso | Cor |
| --- | --- |
| Fundo | `#100F29` |
| Painéis | `#211C42` |
| Ação principal | `#8054E8` |
| Rosa | `#FF8ED9` |
| Azul | `#78D9FF` |
| Texto | `#F4EEFF` |
| Texto secundário | `#BEB4DA` |

Os indicadores de acerto, deslocamento e ausência do Cofre mantêm verde, amarelo e cinza, com símbolos e descrições. As cores decorativas não substituem essas pistas.

O título e os detalhes de navegação usam a fonte monospace do Android; os textos longos mantêm sans-serif. Não há download de fontes.

## Recursos

- `res/drawable-nodpi/controller_arcade.png`: ilustração original do controle, com transparência, usada na apresentação e no ícone.
- `res/mipmap-anydpi/ic_launcher.xml`: composição do ícone para versões anteriores ao Android 8.
- `res/mipmap-anydpi-v26/ic_launcher.xml`: ícone adaptativo.
- `res/drawable/launcher_foreground.xml`: margem segura do ícone adaptativo.
- `res/drawable/launcher_legacy_foreground.xml`: margem do ícone legado.
- `res/drawable/bg_screen.xml` e `grid_arcade.xml`: fundo com grade discreta.
- `res/drawable/painel.xml`, `painel_azul.xml` e `hero.xml`: superfícies.
- `res/drawable/botao_*.xml`: ações principais, secundárias, foco, pressão e desativação.
- `res/layout/item_spinner.xml`: categoria com contraste coerente no campo e no menu.
- `res/layout-land/activity_randomizador.xml`: formulário à esquerda e resultados/lista à direita em telas horizontais.

Os IDs e os tipos dos componentes foram preservados para manter o View Binding. O modelo, a análise textual, as regras da Arena, o motor do Cofre e o sorteio por categoria não foram alterados.
