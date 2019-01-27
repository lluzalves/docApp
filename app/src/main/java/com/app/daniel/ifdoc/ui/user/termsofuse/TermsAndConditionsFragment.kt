package com.app.daniel.ifdoc.ui.user.termsofuse


import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.daniel.ifdoc.R
import kotlinx.android.synthetic.main.fragment_terms_and_conditions.*


class TermsAndConditionsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_terms_and_conditions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        termsAndConditions.text = Html.fromHtml("<p><b>Termos e Condições<p><b>\n" +
                "<p><b>Ifdoc App–  Janeiro 2019<p></b>\n" +
                "<p><b>POR FAVOR LEIA OS TERMOS E CONDIÇÕES ANTES DE USAR A APLICAÇÃO<p></b>\n" +
                "Ao usar esse software ou efetuar registro e login, você está incodicionalmente de acordo com esse termo de uso e condições. Se você não concordar, não use o aplicativo\n" +
                "<p><b>Usuário<p></b>\n" +
                "Ao usar o Ifdoc App, você garante e confirma que:\n" +
                "(i)\ttoda informação submetida, inserida e editar por você é verdadeira e acertiva; \n" +
                "(ii)\tvocê é o responsável por manter a acurácia da informação; \n" +
                "(iii)\tao submeter informações você não está violando nenhuma leia, regulamentação ou orientação em sua localização \n" +
                "(iv)\tvocê aceita receber notificações de nosso sistema e de parceiros\n" +
                "<p><b>Modificações desse termo podem acontecer a qualquer momento<p><b>\n" +
                "Ifdoc App possui o direito de modificar esse termo a qualquer momento nem notificação prévia. Por favor, revisite essa tela de tempos em tempos para se atualizar.\n" +
                "<p><b>Informações<p></b>\n" +
                "O uso de qualquer funcionalidade da aplicação é por sua conta e risco. Não somos responsavéis pelas ações e como você usa a aplicação.\n" +
                "Você concorda que a equipe do Ifdoc App não é responsável por nenhuma perda, dano causado pelo uso dessa aplicação\n")
    }
}

