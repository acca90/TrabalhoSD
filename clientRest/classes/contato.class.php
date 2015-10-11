<?php 
	
	class contato {

		private $contato 	=	array();
		private $url 		= 	"http://localhost/wsRest/index.php/contato";
		private $comando;

		public function __construct($contato) {
			$this->contato = json_encode($contato);
		}

		/* CONEXÃO COM WEBSERVICE */
		public function curl_contato($OPT,$FIELDS) {
		   	$curl = curl_init($this->url . $this->comando);
		   	curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
		   	curl_setopt($curl, $OPT, true);
		   	curl_setopt($curl, $FIELDS, $this->contato);
		   	$curl_response = curl_exec($curl);
		   	curl_close($curl);
		   	return $curl_response;
		}


		/* INSERE CONTATO */
		public function novo() {
			$this->comando = "/novo";
			return $this->curl_contato(CURLOPT_POST,CURLOPT_POSTFIELDS); 
		}

		/* ALTERA CONTATO */
		public function update() {

		}

		/* DELETA CONTATO */
		public function delete() {

		}

		/* LISTA CONTATO */
		public function listar($cidade = "") {

		}




	}


?>