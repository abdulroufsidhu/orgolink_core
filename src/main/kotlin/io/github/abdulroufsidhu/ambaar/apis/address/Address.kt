package io.github.abdulroufsidhu.ambaar.apis.address

import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.github.abdulroufsidhu.ambaar.apis.core.BaseTable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.time.Instant
import java.util.UUID


@JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class"
)
@Entity
@Table(
    name = "addresses",
    uniqueConstraints = [
        UniqueConstraint(
            name = "unique_address",
            columnNames = ["street", "city", "state", "zip", "country"]
        )
    ]
)
data class Address(
    @Column(name = "house_number")
    var houseNumber: String? = null,
    @Column(name = "street")
    var street: String? = null,
    @Column(name = "city")
    var city: String? = null,
    @Column(name = "state")
    var state: String? = null,
    @Column(name = "zip")
    var zip: String? = null,
    @Enumerated(EnumType.STRING)
    var country: Country? = null,
    @Column(name = "id", nullable = false, updatable = false)
    override var id: UUID? = null,
    @Column(name = "created_at", nullable = false, updatable = false)
    override var createdAt: Instant? = null,
    @Column(name = "updated_at", nullable = false)
    override var updatedAt: Instant? = null,
) : BaseTable(id, createdAt, updatedAt) {
    enum class Country(val code: String, val countryName: String, val emoji: String) {
        AFGHANISTAN("AF", "Afghanistan", "\uD83C\uDDE6\uD83C\uDDEB"),
        ALBANIA("AL", "Albania", "\uD83C\uDDE6\uD83C\uDDF1"),
        ALGERIA("DZ", "Algeria", "\uD83C\uDDE9\uD83C\uDDFF"),
        ANDORRA("AD", "Andorra", "\uD83C\uDDE6\uD83C\uDDE9"),
        ANGOLA("AO", "Angola", "\uD83C\uDDE6\uD83C\uDDF4"),
        ANTIGUA_AND_BARBUDA("AG", "Antigua and Barbuda", "\uD83C\uDDE6\uD83C\uDDEC"),
        ARGENTINA("AR", "Argentina", "\uD83C\uDDE6\uD83C\uDDF7"),
        ARMENIA("AM", "Armenia", "\uD83C\uDDE6\uD83C\uDDF2"),
        AUSTRALIA("AU", "Australia", "\uD83C\uDDE6\uD83C\uDDFA"),
        AUSTRIA("AT", "Austria", "\uD83C\uDDE6\uD83C\uDDF9"),
        AZERBAIJAN("AZ", "Azerbaijan", "\uD83C\uDDE6\uD83C\uDDFF"),
        BAHAMAS("BS", "Bahamas", "\uD83C\uDDE7\uD83C\uDDE8"),
        BAHRAIN("BH", "Bahrain", "\uD83C\uDDE7\uD83C\uDDED"),
        BANGLADESH("BD", "Bangladesh", "\uD83C\uDDE7\uD83C\uDDE9"),
        BARBADOS("BB", "Barbados", "\uD83C\uDDE7\uD83C\uDDE7"),
        BELARUS("BY", "Belarus", "\uD83C\uDDE7\uD83C\uDDFE"),
        BELGIUM("BE", "Belgium", "\uD83C\uDDE7\uD83C\uDDEA"),
        BELIZE("BZ", "Belize", "\uD83C\uDDE7\uD83C\uDDFF"),
        BENIN("BJ", "Benin", "\uD83C\uDDE7\uD83C\uDDEF"),
        BHUTAN("BT", "Bhutan", "\uD83C\uDDE7\uD83C\uDDF9"),
        BOLIVIA("BO", "Bolivia", "\uD83C\uDDE7\uD83C\uDDF4"),
        BOSNIA_AND_HERZEGOVINA("BA", "Bosnia and Herzegovina", "\uD83C\uDDE7\uD83C\uDDE6"),
        BOTSWANA("BW", "Botswana", "\uD83C\uDDE7\uD83C\uDDFC"),
        BRAZIL("BR", "Brazil", "\uD83C\uDDE7\uD83C\uDDF7"),
        BRUNEI("BN", "Brunei", "\uD83C\uDDE7\uD83C\uDDF3"),
        BULGARIA("BG", "Bulgaria", "\uD83C\uDDE7\uD83C\uDDEC"),
        BURKINA_FASO("BF", "Burkina Faso", "\uD83C\uDDE7\uD83C\uDDEB"),
        BURUNDI("BI", "Burundi", "\uD83C\uDDE7\uD83C\uDDEE"),
        CABO_VERDE("CV", "Cabo Verde", "\uD83C\uDDE8\uD83C\uDDFB"),
        CAMBODIA("KH", "Cambodia", "\uD83C\uDDF0\uD83C\uDDED"),
        CAMEROON("CM", "Cameroon", "\uD83C\uDDE8\uD83C\uDDF2"),
        CANADA("CA", "Canada", "\uD83C\uDDE8\uD83C\uDDE6"),
        CENTRAL_AFRICAN_REPUBLIC("CF", "Central African Republic", "\uD83C\uDDE8\uD83C\uDDEB"),
        CHAD("TD", "Chad", "\uD83C\uDDF9\uD83C\uDDE9"),
        CHILE("CL", "Chile", "\uD83C\uDDE8\uD83C\uDDF1"),
        CHINA("CN", "China", "\uD83C\uDDE8\uD83C\uDDF3"),
        COLOMBIA("CO", "Colombia", "\uD83C\uDDE8\uD83C\uDDF4"),
        COMOROS("KM", "Comoros", "\uD83C\uDDF0\uD83C\uDDF2"),
        CONGO_DEMOCRATIC_REPUBLIC(
            "CD",
            "Congo, Democratic Republic of the",
            "\uD83C\uDDE8\uD83C\uDDE9"
        ),
        CONGO_REPUBLIC("CG", "Congo, Republic of the", "\uD83C\uDDE8\uD83C\uDDEC"),
        COSTA_RICA("CR", "Costa Rica", "\uD83C\uDDE8\uD83C\uDDF7"),
        CROATIA("HR", "Croatia", "\uD83C\uDDED\uD83C\uDDF7"),
        CUBA("CU", "Cuba", "\uD83C\uDDE8\uD83C\uDDFA"),
        CYPRUS("CY", "Cyprus", "\uD83C\uDDE8\uD83C\uDDFE"),
        CZECH_REPUBLIC("CZ", "Czech Republic", "\uD83C\uDDE8\uD83C\uDDFF"),
        DENMARK("DK", "Denmark", "\uD83C\uDDE9\uD83C\uDDF0"),
        DJIBOUTI("DJ", "Djibouti", "\uD83C\uDDE9\uD83C\uDDEF"),
        DOMINICA("DM", "Dominica", "\uD83C\uDDE9\uD83C\uDDF2"),
        DOMINICAN_REPUBLIC("DO", "Dominican Republic", "\uD83C\uDDE9\uD83C\uDDF4"),
        EAST_TIMOR("TL", "East Timor", "\uD83C\uDDF9\uD83C\uDDF1"),
        ECUADOR("EC", "Ecuador", "\uD83C\uDDEA\uD83C\uDDE8"),
        EGYPT("EG", "Egypt", "\uD83C\uDDEA\uD83C\uDDEC"),
        EL_SALVADOR("SV", "El Salvador", "\uD83C\uDDF8\uD83C\uDDFB"),
        EQUATORIAL_GUINEA("GQ", "Equatorial Guinea", "\uD83C\uDDEC\uD83C\uDDF6"),
        ERITREA("ER", "Eritrea", "\uD83C\uDDEA\uD83C\uDDF7"),
        ESTONIA("EE", "Estonia", "\uD83C\uDDEA\uD83C\uDDEA"),
        ESWATINI("SZ", "Eswatini", "\uD83C\uDDFF\uD83C\uDDF0"),
        ETHIOPIA("ET", "Ethiopia", "\uD83C\uDDEA\uD83C\uDDF9"),
        FIJI("FJ", "Fiji", "\uD83C\uDDEB\uD83C\uDDEF"),
        FINLAND("FI", "Finland", "\uD83C\uDDEB\uD83C\uDDEE"),
        FRANCE("FR", "France", "\uD83C\uDDEB\uD83C\uDDF7"),
        GABON("GA", "Gabon", "\uD83C\uDDEC\uD83C\uDDE6"),
        GAMBIA("GM", "Gambia", "\uD83C\uDDEC\uD83C\uDDF2"),
        GEORGIA("GE", "Georgia", "\uD83C\uDDEC\uD83C\uDDEA"),
        GERMANY("DE", "Germany", "\uD83C\uDDE9\uD83C\uDDEA"),
        GHANA("GH", "Ghana", "\uD83C\uDDEC\uD83C\uDDED"),
        GREECE("GR", "Greece", "\uD83C\uDDEC\uD83C\uDDF7"),
        GRENADA("GD", "Grenada", "\uD83C\uDDEC\uD83C\uDDE9"),
        GUATEMALA("GT", "Guatemala", "\uD83C\uDDEC\uD83C\uDDF9"),
        GUINEA("GN", "Guinea", "\uD83C\uDDEC\uD83C\uDDF3"),
        GUINEA_BISSAU("GW", "Guinea-Bissau", "\uD83C\uDDEC\uD83C\uDDFC"),
        GUYANA("GY", "Guyana", "\uD83C\uDDEC\uD83C\uDDFE"),
        HAITI("HT", "Haiti", "\uD83C\uDDED\uD83C\uDDF9"),
        HONDURAS("HN", "Honduras", "\uD83C\uDDED\uD83C\uDDF3"),
        HUNGARY("HU", "Hungary", "\uD83C\uDDED\uD83C\uDDFA"),
        ICELAND("IS", "Iceland", "\uD83C\uDDEE\uD83C\uDDF8"),
        INDIA("IN", "India", "\uD83C\uDDEE\uD83C\uDDF3"),
        INDONESIA("ID", "Indonesia", "\uD83C\uDDEE\uD83C\uDDE9"),
        IRAN("IR", "Iran", "\uD83C\uDDEE\uD83C\uDDF7"),
        IRAQ("IQ", "Iraq", "\uD83C\uDDEE\uD83C\uDDF6"),
        IRELAND("IE", "Ireland", "\uD83C\uDDEE\uD83C\uDDEA"),
        ISRAEL("IL", "Israel", "\uD83C\uDDEE\uD83C\uDDF1"),
        ITALY("IT", "Italy", "\uD83C\uDDEE\uD83C\uDDF9"),
        IVORY_COAST("CI", "Ivory Coast", "\uD83C\uDDEE\uD83C\uDDF4"),
        JAMAICA("JM", "Jamaica", "\uD83C\uDDEF\uD83C\uDDF2"),
        JAPAN("JP", "Japan", "\uD83C\uDDEF\uD83C\uDDF5"),
        JORDAN("JO", "Jordan", "\uD83C\uDDEF\uD83C\uDDF4"),
        KAZAKHSTAN("KZ", "Kazakhstan", "\uD83C\uDDF0\uD83C\uDDFF"),
        KENYA("KE", "Kenya", "\uD83C\uDDF0\uD83C\uDDEA"),
        KIRIBATI("KI", "Kiribati", "\uD83C\uDDF0\uD83C\uDDEE"),
        KOSOVO("XK", "Kosovo", "\uD83C\uDDFD\uD83C\uDDF0"),
        KUWAIT("KW", "Kuwait", "\uD83C\uDDF0\uD83C\uDDFC"),
        KYRGYZSTAN("KG", "Kyrgyzstan", "\uD83C\uDDF0\uD83C\uDDEC"),
        LAOS("LA", "Laos", "\uD83C\uDDF1\uD83C\uDDE6"),
        LATVIA("LV", "Latvia", "\uD83C\uDDF1\uD83C\uDDFB"),
        LEBANON("LB", "Lebanon", "\uD83C\uDDF1\uD83C\uDDE7"),
        LESOTHO("LS", "Lesotho", "\uD83C\uDDF1\uD83C\uDDF8"),
        LIBERIA("LR", "Liberia", "\uD83C\uDDF1\uD83C\uDDF7"),
        LIBYA("LY", "Libya", "\uD83C\uDDF1\uD83C\uDDFE"),
        LIECHTENSTEIN("LI", "Liechtenstein", "\uD83C\uDDF1\uD83C\uDDEE"),
        LITHUANIA("LT", "Lithuania", "\uD83C\uDDF1\uD83C\uDDF9"),
        LUXEMBOURG("LU", "Luxembourg", "\uD83C\uDDF1\uD83C\uDDFA"),
        MADAGASCAR("MG", "Madagascar", "\uD83C\uDDF2\uD83C\uDDEC"),
        MALAWI("MW", "Malawi", "\uD83C\uDDF2\uD83C\uDDFC"),
        MALAYSIA("MY", "Malaysia", "\uD83C\uDDF2\uD83C\uDDFE"),
        MALDIVES("MV", "Maldives", "\uD83C\uDDF2\uD83C\uDDFB"),
        MALI("ML", "Mali", "\uD83C\uDDF2\uD83C\uDDF1"),
        MALTA("MT", "Malta", "\uD83C\uDDF2\uD83C\uDDF9"),
        MARSHALL_ISLANDS("MH", "Marshall Islands", "\uD83C\uDDF2\uD83C\uDDED"),
        MAURITANIA("MR", "Mauritania", "\uD83C\uDDF2\uD83C\uDDF7"),
        MAURITIUS("MU", "Mauritius", "\uD83C\uDDF2\uD83C\uDDFA"),
        MEXICO("MX", "Mexico", "\uD83C\uDDF2\uD83C\uDDFD"),
        MICRONESIA("FM", "Micronesia", "\uD83C\uDDEB\uD83C\uDDF2"),
        MOLDOVA("MD", "Moldova", "\uD83C\uDDF2\uD83C\uDDE9"),
        MONACO("MC", "Monaco", "\uD83C\uDDF2\uD83C\uDDE8"),
        MONGOLIA("MN", "Mongolia", "\uD83C\uDDF2\uD83C\uDDF3"),
        MONTENEGRO("ME", "Montenegro", "\uD83C\uDDF2\uD83C\uDDEA"),
        MOROCCO("MA", "Morocco", "\uD83C\uDDF2\uD83C\uDDE6"),
        MOZAMBIQUE("MZ", "Mozambique", "\uD83C\uDDF2\uD83C\uDDFF"),
        MYANMAR("MM", "Myanmar", "\uD83C\uDDF2\uD83C\uDDF2"),
        NAMIBIA("NA", "Namibia", "\uD83C\uDDF3\uD83C\uDDE6"),
        NAURU("NR", "Nauru", "\uD83C\uDDF3\uD83C\uDDF7"),
        NEPAL("NP", "Nepal", "\uD83C\uDDF3\uD83C\uDDF5"),
        NETHERLANDS("NL", "Netherlands", "\uD83C\uDDF3\uD83C\uDDF1"),
        NEW_ZEALAND("NZ", "New Zealand", "\uD83C\uDDF3\uD83C\uDDFF"),
        NICARAGUA("NI", "Nicaragua", "\uD83C\uDDF3\uD83C\uDDEE"),
        NIGER("NE", "Niger", "\uD83C\uDDF3\uD83C\uDDEA"),
        NIGERIA("NG", "Nigeria", "\uD83C\uDDF3\uD83C\uDDEC"),
        NORTH_KOREA("KP", "North Korea", "\uD83C\uDDF0\uD83C\uDDF5"),
        NORTH_MACEDONIA("MK", "North Macedonia", "\uD83C\uDDF2\uD83C\uDDF0"),
        NORWAY("NO", "Norway", "\uD83C\uDDF3\uD83C\uDDF4"),
        OMAN("OM", "Oman", "\uD83C\uDDF4\uD83C\uDDF2"),
        PAKISTAN("PK", "Pakistan", "\uD83C\uDDF5\uD83C\uDDF0"),
        PALAU("PW", "Palau", "\uD83C\uDDF5\uD83C\uDDFC"),
        PALESTINE("PS", "Palestine", "\uD83C\uDDF5\uD83C\uDDF8"),
        PANAMA("PA", "Panama", "\uD83C\uDDF5\uD83C\uDDE6"),
        PAPUA_NEW_GUINEA("PG", "Papua New Guinea", "\uD83C\uDDF5\uD83C\uDDEC"),
        PARAGUAY("PY", "Paraguay", "\uD83C\uDDF5\uD83C\uDDFE"),
        PERU("PE", "Peru", "\uD83C\uDDF5\uD83C\uDDEA"),
        PHILIPPINES("PH", "Philippines", "\uD83C\uDDF5\uD83C\uDDED"),
        POLAND("PL", "Poland", "\uD83C\uDDF5\uD83C\uDDF1"),
        PORTUGAL("PT", "Portugal", "\uD83C\uDDF5\uD83C\uDDF9"),
        QATAR("QA", "Qatar", "\uD83C\uDDF6\uD83C\uDDE6"),
        ROMANIA("RO", "Romania", "\uD83C\uDDF7\uD83C\uDDF4"),
        RUSSIA("RU", "Russia", "\uD83C\uDDF7\uD83C\uDDFA"),
        RWANDA("RW", "Rwanda", "\uD83C\uDDF7\uD83C\uDDFC"),
        SAINT_KITTS_AND_NEVIS("KN", "Saint Kitts and Nevis", "\uD83C\uDDF0\uD83C\uDDF3"),
        SAINT_LUCIA("LC", "Saint Lucia", "\uD83C\uDDF1\uD83C\uDDE8"),
        SAINT_VINCENT_AND_THE_GRENADINES(
            "VC",
            "Saint Vincent and the Grenadines",
            "\uD83C\uDDFB\uD83C\uDDE8"
        ),
        SAMOA("WS", "Samoa", "\uD83C\uDDFC\uD83C\uDDF8"),
        SAN_MARINO("SM", "San Marino", "\uD83C\uDDF8\uD83C\uDDF2"),
        SAO_TOME_AND_PRINCIPE("ST", "Sao Tome and Principe", "\uD83C\uDDF8\uD83C\uDDF9"),
        SAUDI_ARABIA("SA", "Saudi Arabia", "\uD83C\uDDF8\uD83C\uDDE6"),
        SENEGAL("SN", "Senegal", "\uD83C\uDDF8\uD83C\uDDF3"),
        SERBIA("RS", "Serbia", "\uD83C\uDDF7\uD83C\uDDF8"),
        SEYCHELLES("SC", "Seychelles", "\uD83C\uDDF8\uD83C\uDDE8"),
        SIERRA_LEONE("SL", "Sierra Leone", "\uD83C\uDDF8\uD83C\uDDF1"),
        SINGAPORE("SG", "Singapore", "\uD83C\uDDF8\uD83C\uDDEC"),
        SLOVAKIA("SK", "Slovakia", "\uD83C\uDDF8\uD83C\uDDF0"),
        SLOVENIA("SI", "Slovenia", "\uD83C\uDDF8\uD83C\uDDEE"),
        SOLOMON_ISLANDS("SB", "Solomon Islands", "\uD83C\uDDF8\uD83C\uDDE7"),
        SOMALIA("SO", "Somalia", "\uD83C\uDDF8\uD83C\uDDF4"),
        SOUTH_AFRICA("ZA", "South Africa", "\uD83C\uDDFF\uD83C\uDDE6"),
        SOUTH_KOREA("KR", "South Korea", "\uD83C\uDDF0\uD83C\uDDF7"),
        SOUTH_SUDAN("SS", "South Sudan", "\uD83C\uDDF8\uD83C\uDDF8"),
        SPAIN("ES", "Spain", "\uD83C\uDDEA\uD83C\uDDF8"),
        SRI_LANKA("LK", "Sri Lanka", "\uD83C\uDDF1\uD83C\uDDF0"),
        SUDAN("SD", "Sudan", "\uD83C\uDDF8\uD83C\uDDF1"),
        SURINAME("SR", "Suriname", "\uD83C\uDDF8\uD83C\uDDF7"),
        SWEDEN("SE", "Sweden", "\uD83C\uDDF8\uD83C\uDDEA"),
        SWITZERLAND("CH", "Switzerland", "\uD83C\uDDE8\uD83C\uDDED"),
        SYRIA("SY", "Syria", "\uD83C\uDDF8\uD83C\uDDFE"),
        TAIWAN("TW", "Taiwan", "\uD83C\uDDF9\uD83C\uDDFC"),
        TAJIKISTAN("TJ", "Tajikistan", "\uD83C\uDDF9\uD83C\uDDEF"),
        TANZANIA("TZ", "Tanzania", "\uD83C\uDDF9\uD83C\uDDFF"),
        THAILAND("TH", "Thailand", "\uD83C\uDDF9\uD83C\uDDED"),
        TOGO("TG", "Togo", "\uD83C\uDDF9\uD83C\uDDEC"),
        TONGA("TO", "Tonga", "\uD83C\uDDF9\uD83C\uDDF4"),
        TRINIDAD_AND_TOBAGO("TT", "Trinidad and Tobago", "\uD83C\uDDF9\uD83C\uDDF9"),
        TUNISIA("TN", "Tunisia", "\uD83C\uDDF9\uD83C\uDDF3"),
        TURKEY("TR", "Turkey", "\uD83C\uDDF9\uD83C\uDDF7"),
        TURKMENISTAN("TM", "Turkmenistan", "\uD83C\uDDF9\uD83C\uDDF2"),
        TUVALU("TV", "Tuvalu", "\uD83C\uDDF9\uD83C\uDDFB"),
        UGANDA("UG", "Uganda", "\uD83C\uDDFA\uD83C\uDDEC"),
        UKRAINE("UA", "Ukraine", "\uD83C\uDDFA\uD83C\uDDE6"),
        UNITED_ARAB_EMIRATES("AE", "United Arab Emirates", "\uD83C\uDDE6\uD83C\uDDEA"),
        UNITED_KINGDOM("GB", "United Kingdom", "\uD83C\uDDEC\uD83C\uDDE7"),
        UNITED_STATES("US", "United States", "\uD83C\uDDFA\uD83C\uDDF8"),
        URUGUAY("UY", "Uruguay", "\uD83C\uDDFA\uD83C\uDDFE"),
        UZBEKISTAN("UZ", "Uzbekistan", "\uD83C\uDDFA\uD83C\uDDFF"),
        VANUATU("VU", "Vanuatu", "\uD83C\uDDFB\uD83C\uDDFA"),
        VATICAN_CITY("VA", "Vatican City", "\uD83C\uDDFB\uD83C\uDDE6"),
        VENEZUELA("VE", "Venezuela", "\uD83C\uDDFB\uD83C\uDDEA"),
        VIETNAM("VN", "Vietnam", "\uD83C\uDDFB\uD83C\uDDF3"),
        YEMEN("YE", "Yemen", "\uD83C\uDDFE\uD83C\uDDEA"),
        ZAMBIA("ZM", "Zambia", "\uD83C\uDDFF\uD83C\uDDF2"),
        ZIMBABWE("ZW", "Zimbabwe", "\uD83C\uDDFF\uD83C\uDDFC");

        companion object {
            fun fromCode(code: String) = entries.find { it.code.equals(code, true) }

            fun fromCountryName(countryName: String) =
                entries.find { it.countryName.equals(countryName, true) }

            fun fromEmoji(emoji: String) =
                entries.find { it.emoji.equals(emoji, true) }
        }
    }

}
