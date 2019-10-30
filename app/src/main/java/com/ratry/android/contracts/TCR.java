package com.ratry.android.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.0.
 */
public class TCR extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b506123f9806100206000396000f3fe608060405234801561001057600080fd5b50600436106101215760003560e01c80637b356561116100ad578063a63dd19811610071578063a63dd19814610249578063d7ab802d14610251578063e2083c1814610264578063fbbf93a014610277578063fc0c546a1461029057610121565b80637b356561146101ef5780637f073360146102135780638a59eb561461021b5780638cf8151f1461022e57806397508f361461024157610121565b80632c729fd1116100f45780632c729fd11461018c57806341b3d1851461019f578063467efe5a146101b4578063691a38ab146101c957806374df2f6d146101dc57610121565b806301a5e3fe1461012657806306fdde031461014f5780630962ef7914610164578063212424c314610179575b600080fd5b61013961013436600461190d565b6102a5565b6040516101469190612061565b60405180910390f35b6101576102c0565b60405161014691906120cf565b61017761017236600461190d565b61034e565b005b6101776101873660046119b2565b610560565b61017761019a366004611965565b610784565b6101a7610996565b6040516101469190612261565b6101bc61099c565b6040516101469190612050565b6101396101d736600461190d565b610aae565b6101776101ea366004611a1a565b610b3a565b6102026101fd36600461190d565b610c45565b60405161014695949392919061206f565b6101a7610da5565b61017761022936600461190d565b610dab565b61013961023c36600461190d565b610de8565b6101a7610dfc565b6101a7610e02565b61015761025f36600461190d565b610e08565b6101a761027236600461192b565b610e7b565b61027f61114a565b6040516101469594939291906120e0565b610298611218565b60405161014691906120c1565b60008181526034602052604090206001015460ff165b919050565b6038805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156103465780601f1061031b57610100808354040283529160200191610346565b820191906000526020600020905b81548152906001019060200180831161032957829003601f168201915b505050505081565b600081815260336020526040902054600160a01b900460ff1615156001146103915760405162461bcd60e51b815260040161038890612221565b60405180910390fd5b600081815260366020908152604080832033845260048101909252909120600281015460ff16156103d45760405162461bcd60e51b815260040161038890612141565b600382015460ff1680156103e95750805460ff165b806104065750600382015460ff161580156104065750805460ff16155b1561054e57600181015460008481526033602052604081206003810154600290910154919261044d929091610441919063ffffffff61122716565b9063ffffffff61124916565b9050600061046882846001015461127790919063ffffffff16565b60375460405163a9059cbb60e01b81529192506001600160a01b03169063a9059cbb9061049b9033908590600401612019565b602060405180830381600087803b1580156104b557600080fd5b505af11580156104c9573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506104ed91908101906118e7565b6105095760405162461bcd60e51b815260040161038890612171565b336001600160a01b0316857f6f4c982acc31b0af2cf1dc1556f21c0325d893782d65e83c68a5534a33f59957836040516105439190612261565b60405180910390a350505b600201805460ff191660011790555050565b610569846102a5565b156105865760405162461bcd60e51b815260040161038890612251565b61058f84610de8565b156105ac5760405162461bcd60e51b815260040161038890612131565b6039548310156105ce5760405162461bcd60e51b815260040161038890612191565b6000848152603460205260409020600181018054610100600160a81b03191633610100021790556106036004820184846115a1565b5060358054600181810180845560009390935260048401805461065d937fcfa4bec1d3298408bb5afcfcd9c430549c5b31f8aa5c5848151c0a55f473c34d019260026101009183161591909102600019019091160461161f565b5050603554600019016005820155603a5461067f90429063ffffffff61127716565b81556002810184905560375460018201546040516323b872dd60e01b81526001600160a01b03928316926323b872dd926106c9926101009091049091169030908990600401612034565b602060405180830381600087803b1580156106e357600080fd5b505af11580156106f7573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061071b91908101906118e7565b6107375760405162461bcd60e51b8152600401610388906121a1565b336001600160a01b0316857fe1cdd401be51ef2c8159945a96274b69ba482f2a5e2131700beeda434fd6d58f8686866040516107759392919061226f565b60405180910390a35050505050565b600083815260346020526040902061079b84610de8565b806107aa5750600181015460ff165b6107c65760405162461bcd60e51b8152600401610388906121d1565b600081600301541180156107f557506003810154600090815260336020526040902054600160a01b900460ff16155b6108115760405162461bcd60e51b815260040161038890612161565b60038101546000908152603660205260409020600281015442106108475760405162461bcd60e51b8152600401610388906121c1565b6037546040516323b872dd60e01b81526001600160a01b03909116906323b872dd9061087b90339030908990600401611ff1565b602060405180830381600087803b15801561089557600080fd5b505af11580156108a9573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506108cd91908101906118e7565b6108e95760405162461bcd60e51b815260040161038890612241565b82156108fa57805484018155610905565b600181018054850190555b604080516060810182528415158152602080820187815260008385018181523380835260048801909452908590209351845490151560ff19918216178555915160018501555160029093018054931515939091169290921790915560038401549151909187917f7a1c5ce18abad3692f6bdf85262d38c3ad4ee5cabf88020e5a0e4d6b841d83769161077591612261565b60395481565b6060806035805490506040519080825280602002602001820160405280156109d857816020015b60608152602001906001900390816109c35790505b50905060005b603554811015610aa757603581815481106109f557fe5b600091825260209182902001805460408051601f6002600019610100600187161502019094169390930492830185900485028101850190915281815292830182828015610a835780601f10610a5857610100808354040283529160200191610a83565b820191906000526020600020905b815481529060010190602001808311610a6657829003601f168201915b5050505050828281518110610a9457fe5b60209081029190910101526001016109de565b5090505b90565b600081815260346020526040812060030154610ac983610de8565b8015610ae2575060008381526034602052604090205442115b8015610af45750610af2836102a5565b155b8015610b225750801580610b225750600081815260336020526040902054600160a01b900460ff1615156001145b15610b315760019150506102bb565b50600092915050565b600054610100900460ff1680610b535750610b53611289565b80610b61575060005460ff16155b610b7d5760405162461bcd60e51b8152600401610388906121f1565b600054610100900460ff16158015610ba8576000805460ff1961ff0019909116610100171660011790555b8351610bbb906038906020870190611694565b50603780546001600160a01b0319166001600160a01b03851617905581518290600090610be457fe5b602002602001015160398190555081600181518110610bff57fe5b6020026020010151603a8190555081600281518110610c1a57fe5b6020908102919091010151603b556000603c558015610c3f576000805461ff00191690555b50505050565b6000806000806060610c55611702565b600087815260346020908152604091829020825160e0810184528154815260018083015460ff8116151583860152610100908190046001600160a01b0316838701526002808501546060850152600385015460808501526004850180548851948116159093026000190190921604601f81018690048602830186019096528582529194929360a086019391929190830182828015610d345780601f10610d0957610100808354040283529160200191610d34565b820191906000526020600020905b815481529060010190602001808311610d1757829003601f168201915b505050505081526020016005820154815250509050610d5287610de8565b80610d5e575080602001515b610d7a5760405162461bcd60e51b8152600401610388906121d1565b602081015160408201516060830151608084015160a090940151929a91995097509195509350915050565b603a5481565b610db481610aae565b15610ddc5760008181526034602052604090206001908101805460ff19169091179055610de5565b610de58161128f565b50565b600090815260346020526040902054151590565b603c5481565b603b5481565b60358181548110610e1557fe5b600091825260209182902001805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152935090918301828280156103465780601f1061031b57610100808354040283529160200191610346565b6000828152603460205260408120610e9284610de8565b80610ea15750600181015460ff165b610ebd5760405162461bcd60e51b8152600401610388906121d1565b60038101541580610ee857506003810154600090815260336020526040902054600160a01b900460ff165b610f045760405162461bcd60e51b815260040161038890612231565b80544210610f245760405162461bcd60e51b815260040161038890612181565b8060020154831015610f485760405162461bcd60e51b815260040161038890612211565b603c80546001908101918290556040805160a081018252338152600060208083018281528385018a8152606085018481526080808701868152998652603385528786209651875494511515600160a01b0260ff60a01b196001600160a01b03929092166001600160a01b031990961695909517169390931786559051968501969096559451600284015594516003909201919091558151928301825280835292820192909252603b54909182019061100790429063ffffffff61127716565b815260006020918201819052603c8054825260368352604091829020845181559284015160018401558382015160028401556060909301516003928301805460ff191691151591909117905591549083015560375490516323b872dd60e01b81526001600160a01b03909116906323b872dd9061108c90339030908890600401611ff1565b602060405180830381600087803b1580156110a657600080fd5b505af11580156110ba573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506110de91908101906118e7565b6110fa5760405162461bcd60e51b815260040161038890612241565b336001600160a01b0316847fc911f55bb4170a166467bde15c8799c1d2bb96a79f6bd9bc5cd3a4f05f768f6f603c546040516111369190612261565b60405180910390a35050603c545b92915050565b6060600080600080606060388054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156111ea5780601f106111bf576101008083540402835291602001916111ea565b820191906000526020600020905b8154815290600101906020018083116111cd57829003601f168201915b5050603754603954603a54603b54969d6001600160a01b039093169c50909a50985093965092945050505050565b6037546001600160a01b031681565b600080821161123557600080fd5b600082848161124057fe5b04949350505050565b60008261125857506000611144565b8282028284828161126557fe5b041461127057600080fd5b9392505050565b60008282018381101561127057600080fd5b303b1590565b60008181526034602052604090206003810154158015906112cb57506003810154600090815260336020526040902054600160a01b900460ff16155b6112e75760405162461bcd60e51b8152600401610388906121e1565b600381015460006112f782611508565b6000838152603360205260409020805460ff60a01b1916600160a01b17908190559091506001600160a01b0316811561137a5760008381526036602090815260408083208054603384528285206003810191909155600191820154828201540160029091015588845260349092529091208101805460ff191690911790556114ce565b6037546000848152603360205260409081902060010154905163a9059cbb60e01b81526001600160a01b039092169163a9059cbb916113be91859190600401612042565b602060405180830381600087803b1580156113d857600080fd5b505af11580156113ec573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061141091908101906118e7565b61142c5760405162461bcd60e51b8152600401610388906121b1565b60008381526036602090815260408083206001808201546033855283862060038082019290925592546002808c0154909101938101939093558a8652603490945291842084815591820180546001600160a81b031916905581018390559081018290559061149d600483018261174a565b6005820160009055505060358460050154815481106114b857fe5b9060005260206000200160006114ce919061174a565b336001600160a01b0316857f779e2848f275ef7b62be066e59dc34b2b8d79758079849859de844a3d9b9fb0d856040516107759190612261565b6000818152603660205260408120600201546115365760405162461bcd60e51b815260040161038890612151565b6000828152603660205260409020600281015442116115675760405162461bcd60e51b815260040161038890612201565b60018101548154106115875760038101805460ff19166001179055611594565b60038101805460ff191690555b6003015460ff1692915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106115e25782800160ff1982351617855561160f565b8280016001018555821561160f579182015b8281111561160f5782358255916020019190600101906115f4565b5061161b92915061178a565b5090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10611658578054855561160f565b8280016001018555821561160f57600052602060002091601f016020900482015b8281111561160f578254825591600101919060010190611679565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106116d557805160ff191683800117855561160f565b8280016001018555821561160f579182015b8281111561160f5782518255916020019190600101906116e7565b6040518060e001604052806000815260200160001515815260200160006001600160a01b03168152602001600081526020016000815260200160608152602001600081525090565b50805460018160011615610100020316600290046000825580601f106117705750610de5565b601f016020900490600052602060002090810190610de591905b610aab91905b8082111561161b5760008155600101611790565b600082601f8301126117b557600080fd5b81356117c86117c3826122c0565b612299565b915081818352602084019350602081019050838560208402820111156117ed57600080fd5b60005b8381101561181957816118038882611839565b84525060209283019291909101906001016117f0565b5050505092915050565b803561114481612390565b805161114481612390565b8035611144816123a4565b8035611144816123ad565b60008083601f84011261186157600080fd5b50813567ffffffffffffffff81111561187957600080fd5b60208301915083600182028301111561189157600080fd5b9250929050565b600082601f8301126118a957600080fd5b81356118b76117c3826122e1565b915080825260208301602083018583830111156118d357600080fd5b6118de83828461234e565b50505092915050565b6000602082840312156118f957600080fd5b6000611905848461182e565b949350505050565b60006020828403121561191f57600080fd5b60006119058484611839565b6000806040838503121561193e57600080fd5b600061194a8585611839565b925050602061195b85828601611839565b9150509250929050565b60008060006060848603121561197a57600080fd5b60006119868686611839565b935050602061199786828701611839565b92505060406119a886828701611823565b9150509250925092565b600080600080606085870312156119c857600080fd5b60006119d48787611839565b94505060206119e587828801611839565b935050604085013567ffffffffffffffff811115611a0257600080fd5b611a0e8782880161184f565b95989497509550505050565b600080600060608486031215611a2f57600080fd5b833567ffffffffffffffff811115611a4657600080fd5b611a5286828701611898565b9350506020611a6386828701611844565b925050604084013567ffffffffffffffff811115611a8057600080fd5b6119a8868287016117a4565b60006112708383611b5c565b611aa181612343565b82525050565b611aa18161231c565b6000611abb8261230f565b611ac58185612313565b935083602082028501611ad785612309565b8060005b85811015611b115784840389528151611af48582611a8c565b9450611aff83612309565b60209a909a0199925050600101611adb565b5091979650505050505050565b611aa181612327565b611aa18161232c565b6000611b3c8385612313565b9350611b4983858461234e565b611b5283612386565b9093019392505050565b6000611b678261230f565b611b718185612313565b9350611b8181856020860161235a565b611b5281612386565b6000611b97602283612313565b7f4c697374696e6720697320616c726561647920696e206170706c792073746167815261329760f11b602082015260400192915050565b6000611bdb601f83612313565b7f566f74652072657761726420697320616c726561647920636c61696d65642e00815260200192915050565b6000611c14601483612313565b732837b636103237b2b9903737ba1032bc34b9ba1760611b815260200192915050565b6000611c44601a83612313565b7f4c697374696e67206973206e6f74206368616c6c656e6765642e000000000000815260200192915050565b6000611c7d601e83612313565b7f566f74696e6720726577617264207472616e73666572206661696c65642e0000815260200192915050565b6000611cb6601783612313565b7f4170706c7920737461676520686173207061737365642e000000000000000000815260200192915050565b6000611cef602183612313565b7f4e6f7420656e6f756768207374616b6520666f72206170706c69636174696f6e8152601760f91b602082015260400192915050565b6000611d32601583612313565b74151bdad95b881d1c985b9cd9995c8819985a5b1959605a1b815260200192915050565b6000611d63601d83612313565b7f4368616c6c656e6765207374616b652072657475726e206661696c6564000000815260200192915050565b6000611d9c601883612313565b7f436f6d6d697420706572696f6420686173207061737365640000000000000000815260200192915050565b6000611dd5601783612313565b7f4c697374696e6720646f6573206e6f742065786973742e000000000000000000815260200192915050565b6000611e0e601983612313565b7f4c697374696e67206973206e6f74206368616c6c656e67656400000000000000815260200192915050565b6000611e47602e83612313565b7f436f6e747261637420696e7374616e63652068617320616c726561647920626581526d195b881a5b9a5d1a585b1a5e995960921b602082015260400192915050565b6000611e97601783612313565b7f436f6d6d697420706572696f6420697320616374697665000000000000000000815260200192915050565b6000611ed0602683612313565b7f4e6f7420656e6f756768207374616b652070617373656420666f72206368616c8152653632b733b29760d11b602082015260400192915050565b6000611f18601a83612313565b7f4368616c6c656e6765206973206e6f74207265736f6c7665642e000000000000815260200192915050565b6000611f51601e83612313565b7f4c697374696e6720697320616c7265616479206368616c6c656e6765642e0000815260200192915050565b6000611f8a601683612313565b752a37b5b2b7103a3930b739b332b9103330b4b632b21760511b815260200192915050565b6000611fbc601f83612313565b7f4c697374696e6720697320616c72656164792077686974656c69737465642e00815260200192915050565b611aa181610aab565b60608101611fff8286611a98565b61200c6020830185611aa7565b6119056040830184611fe8565b604081016120278285611a98565b6112706020830184611fe8565b60608101611fff8286611aa7565b604081016120278285611aa7565b602080825281016112708184611ab0565b602081016111448284611b1e565b60a0810161207d8288611b1e565b61208a6020830187611aa7565b6120976040830186611fe8565b6120a46060830185611fe8565b81810360808301526120b68184611b5c565b979650505050505050565b602081016111448284611b27565b602080825281016112708184611b5c565b60a080825281016120f18188611b5c565b90506121006020830187611aa7565b61210d6040830186611fe8565b61211a6060830185611fe8565b6121276080830184611fe8565b9695505050505050565b6020808252810161114481611b8a565b6020808252810161114481611bce565b6020808252810161114481611c07565b6020808252810161114481611c37565b6020808252810161114481611c70565b6020808252810161114481611ca9565b6020808252810161114481611ce2565b6020808252810161114481611d25565b6020808252810161114481611d56565b6020808252810161114481611d8f565b6020808252810161114481611dc8565b6020808252810161114481611e01565b6020808252810161114481611e3a565b6020808252810161114481611e8a565b6020808252810161114481611ec3565b6020808252810161114481611f0b565b6020808252810161114481611f44565b6020808252810161114481611f7d565b6020808252810161114481611faf565b602081016111448284611fe8565b6040810161227d8286611fe8565b8181036020830152612290818486611b30565b95945050505050565b60405181810167ffffffffffffffff811182821017156122b857600080fd5b604052919050565b600067ffffffffffffffff8211156122d757600080fd5b5060209081020190565b600067ffffffffffffffff8211156122f857600080fd5b506020601f91909101601f19160190565b60200190565b5190565b90815260200190565b600061114482612337565b151590565b60006111448261231c565b6001600160a01b031690565b60006111448261232c565b82818337506000910152565b60005b8381101561237557818101518382015260200161235d565b83811115610c3f5750506000910152565b601f01601f191690565b61239981612327565b8114610de557600080fd5b61239981610aab565b6123998161232c56fea365627a7a72315820f698948c958d3c17ce2685e2ea4cac85b8ef068551e4bea4fed031148768cae36c6578706572696d656e74616cf564736f6c634300050b0040";

    public static final String FUNC_ISWHITELISTED = "isWhitelisted";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_CLAIMREWARDS = "claimRewards";

    public static final String FUNC_PROPOSE = "propose";

    public static final String FUNC_VOTE = "vote";

    public static final String FUNC_MINDEPOSIT = "minDeposit";

    public static final String FUNC_GETALLLISTING = "getAllListing";

    public static final String FUNC_CANBEWHITELISTED = "canBeWhitelisted";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_GETLISTINGDETAILS = "getListingDetails";

    public static final String FUNC_APPLYSTAGELEN = "applyStageLen";

    public static final String FUNC_UPDATESTATUS = "updateStatus";

    public static final String FUNC_APPWASMADE = "appWasMade";

    public static final String FUNC_POLLNONCE = "pollNonce";

    public static final String FUNC_COMMITSTAGELEN = "commitStageLen";

    public static final String FUNC_LISTINGNAMES = "listingNames";

    public static final String FUNC_CHALLENGE = "challenge";

    public static final String FUNC_GETDETAILS = "getDetails";

    public static final String FUNC_TOKEN = "token";

    public static final Event _APPLICATION_EVENT = new Event("_Application", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event _CHALLENGE_EVENT = new Event("_Challenge", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event _VOTE_EVENT = new Event("_Vote", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event _RESOLVECHALLENGE_EVENT = new Event("_ResolveChallenge", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event _REWARDCLAIMED_EVENT = new Event("_RewardClaimed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected TCR(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TCR(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TCR(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TCR(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<Boolean> isWhitelisted(byte[] _listingHash) {
        final Function function = new Function(FUNC_ISWHITELISTED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_listingHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> claimRewards(BigInteger challengeId) {
        final Function function = new Function(
                FUNC_CLAIMREWARDS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(challengeId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> propose(byte[] _listingHash, BigInteger _amount, String _data) {
        final Function function = new Function(
                FUNC_PROPOSE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_listingHash), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.web3j.abi.datatypes.Utf8String(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> vote(byte[] _listingHash, BigInteger _amount, Boolean _choice) {
        final Function function = new Function(
                FUNC_VOTE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_listingHash), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount), 
                new org.web3j.abi.datatypes.Bool(_choice)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> minDeposit() {
        final Function function = new Function(FUNC_MINDEPOSIT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<List> getAllListing() {
        final Function function = new Function(FUNC_GETALLLISTING, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<Boolean> canBeWhitelisted(byte[] _listingHash) {
        final Function function = new Function(FUNC_CANBEWHITELISTED, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_listingHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(String _name, String _token, List<BigInteger> _parameters) {
        final Function function = new Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.Address(160, _token), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.datatypes.generated.Uint256.class,
                        org.web3j.abi.Utils.typeMap(_parameters, org.web3j.abi.datatypes.generated.Uint256.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple5<Boolean, String, BigInteger, BigInteger, String>> getListingDetails(byte[] _listingHash) {
        final Function function = new Function(FUNC_GETLISTINGDETAILS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_listingHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}));
        return new RemoteFunctionCall<Tuple5<Boolean, String, BigInteger, BigInteger, String>>(function,
                new Callable<Tuple5<Boolean, String, BigInteger, BigInteger, String>>() {
                    @Override
                    public Tuple5<Boolean, String, BigInteger, BigInteger, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<Boolean, String, BigInteger, BigInteger, String>(
                                (Boolean) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (String) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> applyStageLen() {
        final Function function = new Function(FUNC_APPLYSTAGELEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> updateStatus(byte[] _listingHash) {
        final Function function = new Function(
                FUNC_UPDATESTATUS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_listingHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Boolean> appWasMade(byte[] _listingHash) {
        final Function function = new Function(FUNC_APPWASMADE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_listingHash)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<BigInteger> pollNonce() {
        final Function function = new Function(FUNC_POLLNONCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> commitStageLen() {
        final Function function = new Function(FUNC_COMMITSTAGELEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> listingNames(BigInteger param0) {
        final Function function = new Function(FUNC_LISTINGNAMES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> challenge(byte[] _listingHash, BigInteger _amount) {
        final Function function = new Function(
                FUNC_CHALLENGE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_listingHash), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple5<String, String, BigInteger, BigInteger, BigInteger>> getDetails() {
        final Function function = new Function(FUNC_GETDETAILS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple5<String, String, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple5<String, String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple5<String, String, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> token() {
        final Function function = new Function(FUNC_TOKEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public List<_ApplicationEventResponse> get_ApplicationEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(_APPLICATION_EVENT, transactionReceipt);
        ArrayList<_ApplicationEventResponse> responses = new ArrayList<_ApplicationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            _ApplicationEventResponse typedResponse = new _ApplicationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.listingHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.applicant = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.deposit = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.data = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<_ApplicationEventResponse> _ApplicationEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, _ApplicationEventResponse>() {
            @Override
            public _ApplicationEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(_APPLICATION_EVENT, log);
                _ApplicationEventResponse typedResponse = new _ApplicationEventResponse();
                typedResponse.log = log;
                typedResponse.listingHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.applicant = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.deposit = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.data = (String) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<_ApplicationEventResponse> _ApplicationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(_APPLICATION_EVENT));
        return _ApplicationEventFlowable(filter);
    }

    public List<_ChallengeEventResponse> get_ChallengeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(_CHALLENGE_EVENT, transactionReceipt);
        ArrayList<_ChallengeEventResponse> responses = new ArrayList<_ChallengeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            _ChallengeEventResponse typedResponse = new _ChallengeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.listingHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.challenger = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.challengeId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<_ChallengeEventResponse> _ChallengeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, _ChallengeEventResponse>() {
            @Override
            public _ChallengeEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(_CHALLENGE_EVENT, log);
                _ChallengeEventResponse typedResponse = new _ChallengeEventResponse();
                typedResponse.log = log;
                typedResponse.listingHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.challenger = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.challengeId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<_ChallengeEventResponse> _ChallengeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(_CHALLENGE_EVENT));
        return _ChallengeEventFlowable(filter);
    }

    public List<_VoteEventResponse> get_VoteEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(_VOTE_EVENT, transactionReceipt);
        ArrayList<_VoteEventResponse> responses = new ArrayList<_VoteEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            _VoteEventResponse typedResponse = new _VoteEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.listingHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.voter = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.challengeId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<_VoteEventResponse> _VoteEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, _VoteEventResponse>() {
            @Override
            public _VoteEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(_VOTE_EVENT, log);
                _VoteEventResponse typedResponse = new _VoteEventResponse();
                typedResponse.log = log;
                typedResponse.listingHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.voter = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.challengeId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<_VoteEventResponse> _VoteEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(_VOTE_EVENT));
        return _VoteEventFlowable(filter);
    }

    public List<_ResolveChallengeEventResponse> get_ResolveChallengeEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(_RESOLVECHALLENGE_EVENT, transactionReceipt);
        ArrayList<_ResolveChallengeEventResponse> responses = new ArrayList<_ResolveChallengeEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            _ResolveChallengeEventResponse typedResponse = new _ResolveChallengeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.listingHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.resolver = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.challengeId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<_ResolveChallengeEventResponse> _ResolveChallengeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, _ResolveChallengeEventResponse>() {
            @Override
            public _ResolveChallengeEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(_RESOLVECHALLENGE_EVENT, log);
                _ResolveChallengeEventResponse typedResponse = new _ResolveChallengeEventResponse();
                typedResponse.log = log;
                typedResponse.listingHash = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.resolver = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.challengeId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<_ResolveChallengeEventResponse> _ResolveChallengeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(_RESOLVECHALLENGE_EVENT));
        return _ResolveChallengeEventFlowable(filter);
    }

    public List<_RewardClaimedEventResponse> get_RewardClaimedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(_REWARDCLAIMED_EVENT, transactionReceipt);
        ArrayList<_RewardClaimedEventResponse> responses = new ArrayList<_RewardClaimedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            _RewardClaimedEventResponse typedResponse = new _RewardClaimedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.challengeId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.voter = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.reward = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<_RewardClaimedEventResponse> _RewardClaimedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, _RewardClaimedEventResponse>() {
            @Override
            public _RewardClaimedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(_REWARDCLAIMED_EVENT, log);
                _RewardClaimedEventResponse typedResponse = new _RewardClaimedEventResponse();
                typedResponse.log = log;
                typedResponse.challengeId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.voter = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.reward = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<_RewardClaimedEventResponse> _RewardClaimedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(_REWARDCLAIMED_EVENT));
        return _RewardClaimedEventFlowable(filter);
    }

    @Deprecated
    public static TCR load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TCR(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TCR load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TCR(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TCR load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TCR(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TCR load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TCR(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TCR> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TCR.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TCR> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TCR.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<TCR> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TCR.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TCR> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TCR.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class _ApplicationEventResponse extends BaseEventResponse {
        public byte[] listingHash;

        public String applicant;

        public BigInteger deposit;

        public String data;
    }

    public static class _ChallengeEventResponse extends BaseEventResponse {
        public byte[] listingHash;

        public String challenger;

        public BigInteger challengeId;
    }

    public static class _VoteEventResponse extends BaseEventResponse {
        public byte[] listingHash;

        public String voter;

        public BigInteger challengeId;
    }

    public static class _ResolveChallengeEventResponse extends BaseEventResponse {
        public byte[] listingHash;

        public String resolver;

        public BigInteger challengeId;
    }

    public static class _RewardClaimedEventResponse extends BaseEventResponse {
        public BigInteger challengeId;

        public String voter;

        public BigInteger reward;
    }
}
