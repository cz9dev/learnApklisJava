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
            list0.add("Debe comprar la aplicación");
            list0.add("Compra verificada");
        } else {
            option = 2;
            list0.add("Usted esta autenticado correctamente");
            list0.add("Ya ha comprado la aplicación");
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
         * De aquí para abajo es la parte del Slide Show
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
     * Uso de Api de pago y actualización
     */

    public void ApiPago() {
        /* BroadcastReceiver Para Manejo De Eventos Asociados A Existencia De Actualización U Obtención De Información De App */

        BroadcastReceiver apklis_update = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                /* Respuesta Del Método getInfo() Valor De La Constante Solicitada */
                Float info = intent.getFloatExtra("info_value", -1);

                /* Respuesta Del Método startLookingForUpdates() Valor De La Versión Name De La App
                Si Existe Una Actualización */
                String version_name = intent.getStringExtra("version_name");


                if (info != -1) {
                  /* Código A Ejecutar Cuando Obtienes Información De La App Usando El Método getInfo()
                     (Ejemplo: Agregar Un Frame Layout Con La Información Obtenida De La App En Apklis) */
                }


                if (version_name != null) {
                  /* Código A Ejecutar Cuando Existe Una Nueva Actualización De La App Usando El Método startLookingForUpdates()
                     (Ejemplo: Agregar Un Frame Layout Con La Información Y Un Botón Que Redirige A La App En Apklis)
                     Si La App No Se Encuentra En Primer Plano Se Lanza Una Notificación Con La Información
                     De Que Existe Una Nueva Versión Y La Version Name */

                }

            }
        };


        /* Registro De Recibidores Para Manejar Existencia De Actualización U Obtención De Info Respectivamente */

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(apklis_update, new IntentFilter("apklis_update"));

        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(apklis_update, new IntentFilter("apklis_app_info"));

        /* Instanciar Un Objeto De La Clase ApklisUtil */
        //ApklisUtil apklis = new ApklisUtil(this, this.getPackageName());
        ApklisUtil apklis = new ApklisUtil(this, PACKAGE_ID);

        /* Método Para Verificar Pago. Es Necesario Tener Instalada La App De Apklis
         Y Una Sesión Activa En Esta De Lo Contrario Devolverá false */

        this.paid = apklis.checkPaymentApp();

        /* Método Para Obtener UserName. Es Necesario Tener Instalada La App De Apklis
         Y Una Sesión Activa En Esta De Lo Contrario Devolverá null */

        this.username = apklis.getUserName();

      /* Método Para Verificar Si Existe Una Actualización. Sobrecarga de Métodos.
       La Respuesta De Este Método Se Maneja En El BroadcastReceiver
         Si La App Está En Primer Plano Si No Se Lanza Una Notificación */

        apklis.startLookingForUpdates(2); /* Se Ejecuta Una Sola Vez Cuando Es Llamado. Recibe Como Parámetro Un Entero Que Representa
                                                      El Tiempo En Segundos Que Debe Transcurrir Como Mínimo Desde La Llamada Hasta La Ejecución */

        apklis.startLookingForUpdates(20, true); /* Se Ejecuta Periódicamente. Recibe Como Parámetros Un Entero Que Representa
                                                                          El Tiempo En Minutos Que Debe Transcurrir Entre Una Búsqueda Y Otra (Para
                                                                          Android N Y Versiones Superiores El Tiempo Mínimo Permitido Es 15 Minutos)
                                                                          Y Un Booleano, Este último Valor Es Irrelevante. */


        /* Método Para Actualizar Información Básica Sobre La App.
        La Respuesta De Este Método Se Maneja En El BroadcastReceiver */

        /*
        apklis.getInfo(apklis.DOWNLOADS); /* Recibe Como Parámetro Un String (Posibles Valores Constantes:
                                                                                             apklis.DOWNLOADS: Número De Descargas
                                                                                             apklis.SALES: Número De Ventas
                                                                                             apklis.RATING: Calificación
                                                                                             apklis.REVIEWS: Número De Reviews
                                                                                             apklis.PRICE: Precio
                                                                                             apklis.REVIEWS_STAR_1: Número De Reviews 1 Estrella
                                                                                             apklis.REVIEWS_STAR_2: Número De Reviews 2 Estrellas
                                                                                             apklis.REVIEWS_STAR_3: Número De Reviews 3 Estrellas
                                                                                             apklis.REVIEWS_STAR_4: Número De Reviews 4 Estrellas
                                                                                             apklis.REVIEWS_STAR_5: Número De Reviews 5 Estrellas */


    }

    /**
     * FUNCION PARA REENVIAR A APKLIS A VALORAR APP
     */
    public void rateAction(Uri uri) {
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(goToMarket);
    }
}