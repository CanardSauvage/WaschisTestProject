/*
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package de.waschnick.ideas;

import org.jsmpp.InvalidResponseException;
import org.jsmpp.PDUException;
import org.jsmpp.bean.Alphabet;
import org.jsmpp.bean.BindType;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.GeneralDataCoding;
import org.jsmpp.bean.MessageClass;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.SMSCDeliveryReceipt;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ResponseTimeoutException;
import org.jsmpp.session.BindParameter;
import org.jsmpp.session.SMPPSession;
import org.jsmpp.util.AbsoluteTimeFormatter;
import org.jsmpp.util.TimeFormatter;

import java.io.IOException;
import java.util.Date;

public class SimpleSubmitExample {

    private static TimeFormatter timeFormatter = new AbsoluteTimeFormatter();

    // Session-Parameter
    public static final String HOST = "localhost";//"193.7.141.206";
    public static final int PORT = 9999;// 17600;
    public static final String SYSTEM_ID = "bilddi";
    public static final String PASSWORD = "fGr3K2m";
    public static final String SYSTEM_TYPE = "cp";
    public static final TypeOfNumber TYPE_OF_NUMBER = TypeOfNumber.UNKNOWN;
    public static final NumberingPlanIndicator ADDR_NPI = NumberingPlanIndicator.UNKNOWN;
    public static final BindType BIND_TYPE = BindType.BIND_TX;

    // eplus.smppUri=smpp://bilddi@193.7.141.206:17600?password=fGr3K2m&lazySessionCreation=true&sourceAddr=BILDplus&sourceAddrTon=5&sourceAddrNpi=0&destAddrTon=1&destAddrNpi=1&numberingPlanIndicator=9&systemType=IPA&serviceType=
    // eplus.smppUri=smpp://bilddi@193.7.141.206:17600?
    // password=fGr3K2m&
    // lazySessionCreation=true&
    // sourceAddr=BILDplus&
    // sourceAddrTon=5&
    // sourceAddrNpi=0&
    // destAddrTon=1&
    // destAddrNpi=1&
    // numberingPlanIndicator=9&
    // systemType=IPA
    // &serviceType=


    public static void main(String[] args) {
        SMPPSession session = new SMPPSession();
        try {
            // FIXME Remove System.out
            System.out.println("Connect and bind: START");
            session.connectAndBind(HOST, PORT, new BindParameter(BIND_TYPE, SYSTEM_ID, PASSWORD, SYSTEM_TYPE, TYPE_OF_NUMBER, ADDR_NPI, null));
            // FIXME Remove System.out
            System.out.println("Connect and bind: FINISHED");
        } catch (IOException e) {
            System.err.println("Failed connect and bind to host");
            e.printStackTrace();
        }

        try {
            // FIXME Remove System.out
            System.out.println("Try to submit message.");
            String messageId = session.submitShortMessage(
                    "CMT", //ServiceType
                    TypeOfNumber.UNKNOWN,//sourceAddrTon
                    NumberingPlanIndicator.UNKNOWN, //sourceAddrNPI
                    "1616",//sourceAddress
                    TypeOfNumber.NATIONAL, //destAddrTon
                    NumberingPlanIndicator.UNKNOWN,
                    "491797382718",
                    new ESMClass(),
                    (byte) 0,
                    (byte) 1,
                    timeFormatter.format(new Date()),
                    null,
                    new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT),
                    (byte) 0,
                    new GeneralDataCoding(Alphabet.ALPHA_DEFAULT, MessageClass.CLASS1, false),
                    (byte) 0,
                    "jSMPP simplify SMPP on Java platform".getBytes());

            System.out.println("Message submitted, message_id is " + messageId);
        } catch (PDUException e) {
            // Invalid PDU parameter
            System.err.println("Invalid PDU parameter");
            e.printStackTrace();
        } catch (ResponseTimeoutException e) {
            // Response timeout
            System.err.println("Response timeout");
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            // Invalid response
            System.err.println("Receive invalid respose");
            e.printStackTrace();
        } catch (NegativeResponseException e) {
            // Receiving negative response (non-zero command_status)
            System.err.println("Receive negative response");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IO error occur");
            e.printStackTrace();
        }

        session.unbindAndClose();
    }


}