package butatopanto

class HeisigTagLib {

  static namespace = "heisig"

  def frameCard = { attributes ->
    def frame = attributes.frame;
    def function = 'reveal("' + frame?.kanji + '")'
    out << "<div style='width:270px; height:390px ;position:relative; top:50px; background-color:white;valign:middle' align='center' onclick='" + function + "'> "
    out << "<table height = '100%'>"
    out << "<tr height = '10%'> "
    out << "<td style = 'text-align: left; font-size:20px'>${frame?.meaning}</td>"
    out << "</tr>"
    out << "<tr>"
    out << "<td id = 'character' height = '100%' style = 'text-align: center; vertical-align:middle; font-size:100px'> ? </td>"
    out << "</tr>"
    out << "<tr height = '10%' >"
    out << "<td id = 'number' style = 'text-align: right; visibility: hidden; font-size:12px'>${frame?.number}</td>"
    out << "</tr>"
    out << "</table>"
    out << "</div>"
  }
}
