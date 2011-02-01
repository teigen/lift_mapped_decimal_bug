package bootstrap.liftweb

import net.liftweb.mapper._
import xml.NodeSeq
import net.liftweb.http.{RedirectResponse, LiftRules}
import net.liftweb.sitemap.{Menu, Loc, SiteMap}
import net.liftweb.common.Full

class Boot {
  def entries =
    (Menu("Homepage") / "index" >> Loc.EarlyResponse(() => Full(RedirectResponse("/demo/list")))) :: Demo.menus

  LiftRules.setSiteMap(SiteMap(entries :_*))
  DB.defineConnectionManager(DefaultConnectionIdentifier, Database)
  Schemifier.schemify(true, Schemifier.infoF _, Demo)
}

object Database extends StandardDBVendor("org.h2.Driver", "jdbc:h2:mem:demo;DB_CLOSE_DELAY=-1", None, None)

class Demo extends LongKeyedMapper[Demo] with IdPK {
  def getSingleton = Demo
  object decimal extends MappedDecimal(this, BigDecimal(0))
  object int extends MappedInt(this)
  object long extends MappedLong(this)
}

object Demo extends Demo with LongKeyedMetaMapper[Demo] with CRUDify[Long, Demo]{
  override def pageWrapper(body:NodeSeq) =
    <lift:surround with="default" at="content">
      try writing something thats NOT a number in the decimal field (even submitting the field empty will do)
      {body}
    </lift:surround>
}