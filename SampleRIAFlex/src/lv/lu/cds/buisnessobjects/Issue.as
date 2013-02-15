package lv.lu.cds.buisnessobjects
{
	[Bindable]
	public class Issue
	{
		public var id : int;
		public var status : String;
		public var author : String;
		public var created : Date;
		public var title : String;
		public var description : String;
		public var priority : String;
		public var system : String;
		public var functionality : String;
		public var risk : String;
		public var responder : String;
		public var notes : String;
	}
}