<?php 
	
	class contato {

		private $contato 	=	array();
		private $url 		= 	"http://localhost/wsRest/index.php/contato";
		private $comando;

		public function __construct($contato) {
			$this->contato = json_encode($contato);
		}

		/* CONEXÃO COM WEBSERVICE */
		public function curl_contato($OPT,$TIPO,$GET = false) {
		   	$curl = curl_init($this->url . $this->comando);
		   	curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
		   	
		   	/* PARA METODOS POST, PUT e DELETE */
		   	if (!$GET) curl_setopt($curl, $OPT, $TIPO);
		   	if (!$GET) curl_setopt($curl, CURLOPT_POSTFIELDS, $this->contato);

		   	$curl_response = curl_exec($curl);
		   	curl_close($curl);
		   	return $curl_response;
		}

		/* INSERE CONTATO */
		public function novo() {
			return $this->curl_contato(CURLOPT_POST,true); 
		}

		/* ALTERA CONTATO */
		public function update() {
			return $this->curl_contato(CURLOPT_CUSTOMREQUEST,"PUT"); 
		}

		/* DELETA CONTATO */
		public function delete() {
			return $this->curl_contato(CURLOPT_CUSTOMREQUEST,"DELETE"); 
		}

		/* LISTA CONTATO */
		public function listar() {
			foreach (json_decode($this->contato) as $dado) {
				if (str_replace("contato_","",$dado->name) == "cidade") { 
					if (strlen($dado->value) > 0)
						$this->comando = "/" . urlencode($dado->value);
				}
			}
			return $this->curl_contato("","",true); 
		}




	}


?>