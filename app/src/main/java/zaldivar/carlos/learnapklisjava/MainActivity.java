package zaldivar.carlos.learnapklisjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baoyachi.stepview.VerticalStepView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

import zaldivar.carlos.learnapklisjava.util.ApklisUtil;

public class MainActivity extends AppCompatActivity {
    public String PACKAGE_ID = "zaldivar.carlos.learnapklis"; //nombre del paquete como esta en apklis
    Boolean paid;
    String username;
    Button btnComprarApk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnComprarApk = (Button) findViewById(R.id.btn_comprar_apk);

        // Ejecutar codigo del api
        this.ApiPago();

        /**
         * Crear vista de lista para los requerimientos
         */

        VerticalStepView myStepView = (VerticalStepView) findViewById(R.id.myStepView);
        // Crear lista
        ArrayList<String> list0 = new ArrayList<>();
        // Variable que guardara posicion activa
        int option = 0;

        /**
         * Creando valores de la lista dependiendo de estado de compra
         */

        if (username == null) {
            option = 0;
            list0.add("Debes estar autenticado");
            list0.add("Debes compara la aplicacion");
            list0.add("Compra verificada");
        } else if (!paid) {
            option = 1;
            list0.add("Usted esta autenticado correctamente");
            list0.add("Debe comprar la aplicaci??n");
            list0.add("Compra verificada");
        } else {
            option = 2;
            list0.add("Usted esta autenticado correctamente");
            list0.add("Ya ha comprado la aplicaci??n");
            list0.add("Compra verificada, Usted puede continuar");
            btnComprarApk.setVisibility(Button.INVISIBLE);
        }


        /**
         * Formatear quien StepView
         */
        myStepView.setStepsViewIndicatorComplectingPosition(option)
                .reverseDraw(false)
                .setTextSize(16)
                .setStepViewTexts(list0)
                .setStepsViewIndicatorCompletedLineColor(
                        ContextCompat.getColor(
                                this,
                                R.color.primaryTextColor
                        )
                )
                .setStepsViewIndicatorUnCompletedLineColor(
                        ContextCompat.getColor(
                                this,
                                R.color.primaryTextColor
                        )
                )
                .setStepViewComplectedTextColor(
                        ContextCompat.getColor(
                                this,
                                R.color.black
                        )
                )
                .setStepViewUnComplectedTextColor(
                        ContextCompat.getColor(
                                this,
                                R.color.black
                        )
                )
                .setStepsViewIndicatorCompleteIcon(
                        ContextCompat.getDrawable(
                                this,
                                R.drawable.ic_check
                        )
                )
                .setStepsViewIndicatorDefaultIcon(
                        ContextCompat.getDrawable(
                                this,
                                R.drawable.ic_unchecked
                        )
                )
                .setStepsViewIndicatorAttentionIcon(
                        ContextCompat.getDrawable(
                                this,
                                R.drawable.ic_info
                        )
                );

        /**
         * De aqu?? para abajo es la parte del Slide Show
         */

        ArrayList<SlideModel> imageList = new ArrayList<SlideModel>();

        imageList.add(new SlideModel(R.drawable.baner1, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.baner2, ScaleTypes.FIT));
        imageList.add(new SlideModel(R.drawable.baner3, ScaleTypes.FIT));

        ImageSlider imageSlider = (ImageSlider) findViewById(R.id.image_slide);
        imageSlider.setImageList(imageList);

        /**
         *  Usar click listener para enlazar a aplicaciones en apklis.
         */
        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Uri uri;
                switch (i) {
                    case 0:
                        uri = Uri.parse("https://www.apklis.cu/application/zaldivar.carlos.calcelect");
                        rateAction(uri);
                    case 1:
                        uri = Uri.parse("https://www.apklis.cu/application/cu.apklis.unehogar");
                        rateAction(uri);
                    default:
                        uri = Uri.parse("https://www.apklis.cu/application/cu.cubava.hidro");
                        rateAction(uri);
                }
            }
        });

        /**
         * Impementacion de la compra
         */

        Uri uri = Uri.parse("https://www.apklis.cu/application/zaldivar.carlos.learnapklis");
        btnComprarApk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateAction(uri);
            }
        });

    }

    /**
     * Uso de Api de pago y actualizaci??n
     */

    public void ApiPago() {
        /* BroadcastReceiver Para Manejo De Eventos Asociados A Existencia De Actualizaci??n U Obtenci??n De Informaci??n De App */

        BroadcastReceiver apklis_update = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                /* Respuesta Del M??todo getInfo() Valor De La Constante Solicitada */
                Float info = intent.getFloatExtra("info_value", -1);

                /* Respuesta Del M??todo startLookingForUpdates() Valor De La Versi??n Name De La App
                Si Existe Una Actualizaci??n */
                String version_name = intent.getStringExtra("version_name");


                if (info != -1) {
                  /* C??digo A Ejecutar Cuando Obtienes Informaci??n De La App Usando El M??todo getInfo()
                     (Ejemplo: Agregar Un Frame Layout Con La Informaci??n Obtenida De La App En Apklis) */
                }


                if (version_name != null) {
                  /* C??digo A Ejecutar Cuando Existe Una Nueva Actualizaci??n De La App Usando El M??todo startLookingForUpdates()
                     (Ejemplo: Agregar Un Frame Layout Con La Informaci??n Y Un Bot??n Que Redirige A La App En Apklis)
                     Si La App No Se Encuentra En Primer Plano Se Lanza Una Notificaci??n Con La Informaci??n
                     De Que Existe Una Nueva Versi??n Y La Version Name */

                }

            }
        };


        /* Registro De Recibidores Para Manejar Existencia De Actualizaci??n U Obtenci??n De Info Respectivamente */

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(apklis_update, new IntentFilter("apklis_update"));

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(apklis_update, new IntentFilter("apklis_app_info"));

        /* Instanciar Un Objeto De La Clase ApklisUtil */
        //ApklisUtil apklis = new ApklisUtil(this, this.getPackageName());
        ApklisUtil apklis = new ApklisUtil(this, PACKAGE_ID);

        /* M??todo Para Verificar Pago. Es Necesario Tener Instalada La App De Apklis
         Y Una Sesi??n Activa En Esta De Lo Contrario Devolver?? false */

        this.paid = apklis.checkPaymentApp();

        /* M??todo Para Obtener UserName. Es Necesario Tener Instalada La App De Apklis
         Y Una Sesi??n Activa En Esta De Lo Contrario Devolver?? null */

        this.username = apklis.getUserName();

      /* M??todo Para Verificar Si Existe Una Actualizaci??n. Sobrecarga de M??todos.
       La Respuesta De Este M??todo Se Maneja En El BroadcastReceiver
         Si La App Est?? En Primer Plano Si No Se Lanza Una Notificaci??n */

        apklis.startLookingForUpdates(2); /* Se Ejecuta Una Sola Vez Cuando Es Llamado. Recibe Como Par??metro Un Entero Que Representa
                                                      El Tiempo En Segundos Que Debe Transcurrir Como M??nimo Desde La Llamada Hasta La Ejecuci??n */

        apklis.startLookingForUpdates(20, true); /* Se Ejecuta Peri??dicamente. Recibe Como Par??metros Un Entero Que Representa
                                                                          El Tiempo En Minutos Que Debe Transcurrir Entre Una B??squeda Y Otra (Para
                                                                          Android N Y Versiones Superiores El Tiempo M??nimo Permitido Es 15 Minutos)
                                                                          Y Un Booleano, Este ??ltimo Valor Es Irrelevante. */


        /* M??todo Para Actualizar Informaci??n B??sica Sobre La App.
        La Respuesta De Este M??todo Se Maneja En El BroadcastReceiver */

        /*
        apklis.getInfo(apklis.DOWNLOADS); /* Recibe Como Par??metro Un String (Posibles Valores Constantes:
                                                                                             apklis.DOWNLOADS: N??mero De Descargas
                                                                                             apklis.SALES: N??mero De Ventas
                                                                                             apklis.RATING: Calificaci??n
                                                                                             apklis.REVIEWS: N??mero De Reviews
                                                                                             apklis.PRICE: Precio
                                                                                             apklis.REVIEWS_STAR_1: N??mero De Reviews 1 Estrella
                                                                                             apklis.REVIEWS_STAR_2: N??mero De Reviews 2 Estrellas
                                                                                             apklis.REVIEWS_STAR_3: N??mero De Reviews 3 Estrellas
                                                                                             apklis.REVIEWS_STAR_4: N??mero De Reviews 4 Estrellas
                                                                                             apklis.REVIEWS_STAR_5: N??mero De Reviews 5 Estrellas */


    }

    /**
     * FUNCION PARA REENVIAR A APKLIS A VALORAR APP
     */
    public void rateAction(Uri uri) {
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(goToMarket);
    }
}