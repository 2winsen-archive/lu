package lv.lu.cds.model.service
{
	import mx.rpc.soap.WebService;

	public class SampleRIAService implements ISampleRIAService
	{
		private var webService : WebService;
		
		public function SampleRIAService( channelUrl : String )
		{
			setupWebService( channelUrl );
		}
		
		private function setupWebService( channelUrl : String ) : void
		{
			webService = new WebService;
			webService.useProxy = false;
			webService.loadWSDL(channelUrl);
		}
		
		public function Login( json : String, resultHandler : Function = null, faultHandler : Function = null ) : void
		{
			if ( webService != null )
			{
				webService.Login.addEventListener("result", resultHandler);
				webService.addEventListener("fault", faultHandler);
				webService.Login( json );
			}
		}
	}
}