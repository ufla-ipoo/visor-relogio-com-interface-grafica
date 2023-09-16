
/**
 * A classe VisorRelogio implementa um vistor de relógio digital.
 * O relógio mostra horas e minutos. O relógio marca de 00:00 (meia-noite)
 * até 23:59 (um minuto para meia-noite).
 * 
 * O visor do relógio recebe "tique-taques" (através do método tiqueTaque) a
 * cada minuto e reage incrementado o valor do visor. Isso é feito como é em
 * um relógio, a hora incrementa quando os minutos voltam para zero.
 * 
 * Traduzido por Julio César Alves - 2023.09.15
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class VisorRelogio
{
    private VisorNumero horas;
    private VisorNumero minutos;
    private String stringVisor;    // simula o visor real
    
    /**
     * Construtor para objetos VisorRelogio. Este construtor
     * cria um novo relógio marcando 00:00.
     */
    public VisorRelogio()
    {
        horas = new VisorNumero(24);
        minutos = new VisorNumero(60);
        atualizarVisor();
    }

    /**
     * Construtor para objetos VisorRelogio. Este construtor
     * cria um novo relógio marcando as horas de acordo com 
     * os parâmetros recebidos.
     */
    public VisorRelogio(int hora, int minuto)
    {
        horas = new VisorNumero(24);
        minutos = new VisorNumero(60);
        definirHora(hora, minuto);
    }

    /**
     * Este método deveria ser chamado uma vez a cada minuto -
     * ele faz o visor do relógio passar um minuto.
     */
    public void tiqueTaque()
    {
        minutos.incrementar();
        if(minutos.obterValor() == 0) {  // minutos voltaram para zero
            horas.incrementar();
        }
        atualizarVisor();
    }

    /**
     * Define a hora do visor de acordo com o valor de hora e
     * minuto recebidos por parâmetro.
     */
    public void definirHora(int hour, int minute)
    {
        horas.definirValor(hour);
        minutos.definirValor(minute);
        atualizarVisor();
    }

    /**
     * Retorna a hora atual do visor no formato HH:MM.
     */
    public String obterHora()
    {
        return stringVisor;
    }
    
    /**
     * Atualiza a string interna que representa o visor.
     */
    private void atualizarVisor()
    {
        stringVisor = horas.obterValorVisor() + ":" + 
                        minutos.obterValorVisor();
    }
}
