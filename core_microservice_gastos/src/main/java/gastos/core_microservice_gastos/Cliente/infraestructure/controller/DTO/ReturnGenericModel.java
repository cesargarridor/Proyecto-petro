package gastos.core_microservice_gastos.Cliente.infraestructure.controller.DTO;

public class ReturnGenericModel<T> {

	public T objeto;
	public String infoMessage;
	public String codError;

	/**
	 * Constructor por defecto
	 */
	public ReturnGenericModel() {

	}

	/**
	 * Constructor por parametros
	 * 
	 * @param objeto      T
	 * @param infoMessage Strig
	 * @param codError    Enum
	 */
	public ReturnGenericModel(T objeto, String infoMessage, Enum<?> codError) {
		super();
		this.objeto = objeto;
		this.infoMessage = infoMessage;
		if (codError != null)
			this.codError = codError.name();
	}

	/**
	 * Constructor por parametros.
	 *
	 * @param objeto      T
	 * @param infoMessage Strig
	 */
	public ReturnGenericModel(T objeto, String infoMessage) {
		super();
		this.objeto = objeto;
		this.infoMessage = infoMessage;
	}

	/**
	 * Metodo que devuelve el objeto
	 * 
	 * @return objeto Objet
	 */
	public T getObjeto() {
		return objeto;
	}

	/**
	 * Metodo que registra el objeto
	 * 
	 * @param objeto Objet T
	 */
	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

	/**
	 * Metodo que devuelve el infoMessage
	 * 
	 * @return infoMessage String
	 */
	public String getInfoMessage() {
		return infoMessage;
	}

	/**
	 * Metodo que registra el infoMessage
	 * 
	 * @param infoMessage String
	 */
	public void setInfoMessage(String infoMessage) {
		this.infoMessage = infoMessage;
	}

	/**
	 * Metodo que obtiene el codigo de error
	 * 
	 * @return String
	 */
	public String getCodError() {
		return codError;
	}

	/**
	 * Metodo que establece el codigo de error
	 * 
	 * @param codError Enum
	 */
	public void setCodError(String codError) {
		this.codError = codError;
	}

}
