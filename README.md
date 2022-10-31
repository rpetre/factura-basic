# factura-basic

Aceasta este o reimpachetare a aplicatiei sample publicata de Ministerul de Finante la https://mfinante.gov.ro/web/efactura/informatii-tehnice pentru "conversie XML in PDF", in scopul de a putea fi folosita headless (doar interfata CLI).

## Important!

- licenta MIT este cu titlu provizoriu, pana ma lamuresc care e cu adevarat copyrightul programului original (si in special a template-urilor Jasper Reports folosite, unde e adevarata munca)
- este posibil ca acest repo sa fie rescris complet sau chiar redenumit pe viitor, experienta mea cu Java e practic zero si probabil o sa vin cu idei mai bune de nume si/sau versionare
- codul nu a fost testat decat orientativ pe unul din sample-urile oferite in pagina de mai sus si doar pe OpenJDK 17. In particular ignora cu desavarsire posibilele atasamente binare.

## instructiuni de build

Am atasat un build.sh care se foloseste de docker sa asambleze jar-ul final ( target/factura-1.0-jar-with-dependencies.jar , nu m-am lamurit cum se suprima celalalt). Se foloseste un subfolder .m2 ca cache de dependinte pentru builduri ulterioare.

## instructiuni de utilizare

    java -jar nume.jar nume_fisier.xml

Comanda de mai sus va produce un fisier numit output.pdf in folderul cu fisierul .jar . Daca nu se specifica un nume de fisier, il va folosi pe cel din samples.

## TODO (posibil niciodata)

- lamurit situatia licentei
- mutat fisierele jasper in interiorul jar-ului pentru usurinta operationala, preferabil doar cele care sunt folosite.
- de implementat o forma de --input si --output care sa suporte inclusiv mai multe fisiere
- de readaugat handlingul de atasamente
